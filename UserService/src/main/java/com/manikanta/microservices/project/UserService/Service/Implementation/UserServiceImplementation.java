package com.manikanta.microservices.project.UserService.Service.Implementation;

//import com.manikanta.microservices.project.UserService.Controller.JWTService;
import com.manikanta.microservices.project.UserService.DTO.*;
import com.manikanta.microservices.project.UserService.Entity.User;
import com.manikanta.microservices.project.UserService.Enums.MyFeatures;
import com.manikanta.microservices.project.UserService.Exception.EmailAlreadyFoundException;
import com.manikanta.microservices.project.UserService.Exception.UserNotFoundException;
import com.manikanta.microservices.project.UserService.Mapper.AutoUserMapper;
import com.manikanta.microservices.project.UserService.Repository.UserRepository;
import com.manikanta.microservices.project.UserService.Service.FeignAPIClient;
import com.manikanta.microservices.project.UserService.Service.FeignWeblabClient;
import com.manikanta.microservices.project.UserService.Service.UserService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.togglz.core.manager.FeatureManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImplementation implements UserService {

//    @Autowired
//    private JWTService jwtService;

    @Autowired
    private FeatureManager featureManager;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

//    @Autowired
//    private RestTemplate  restTemplate;

//    @Autowired
//            private WebClient webClient;

    @Autowired
            private FeignAPIClient feignAPIClient;

    @Autowired
    private FeignWeblabClient feignWeblabClient;

//    @Autowired
//    AuthenticationManager authManager;

//    @Autowired
//    private ModelMapper mapper;

//    @Cacheable(value = "getUserById", key = "#userId")
    @Override
    public UserDTO getUser(Long userId){
        logger.info("entered into get user by id method");
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));
        System.out.println("Using the new checkout feature.");
        return AutoUserMapper.MAPPER.mapToDTO(user);
//        if (featureManager.isActive(MyFeatures.NEW_CHECKOUT_FEATURE)) {
//            // New checkout feature logic
//            User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));
//            System.out.println("Using the new checkout feature.");
//            return AutoUserMapper.MAPPER.mapToDTO(user);
//        } else {
//            // Fallback to old checkout logic
//            System.out.println("Using the old checkout feature.");
//            User user = userRepository.findById(Long.valueOf(1)).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));
//            return AutoUserMapper.MAPPER.mapToDTO(user);
//        }
    }

    @Override
    public UserDTO getUserDefault(){
        logger.info("entered into get user by id method");
        User user = userRepository.findById(1L).orElseThrow(()->new UserNotFoundException("No User Found with this userId: Default"));
        System.out.println("Using the new checkout feature.");
        return AutoUserMapper.MAPPER.mapToDTO(user);
    }


    @Override
    @CacheEvict(value = "getUserById", key = "#userId")
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("Deleted");
    }
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Override
    public void addUser(User user) {
        User userOne = userRepository.findByEmail(user.getEmail());
        if(userOne != null){
            throw new EmailAlreadyFoundException("Email Already found in database");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("saved to db");
    }

    @Override
    public UserResponse getUsers(int pageNo, int pageSize, String pageSortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(pageSortBy).ascending():
                Sort.by(pageSortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        logger.info("entered into get all user method");

        Page<User> users = userRepository.findAll(pageable);

        List<UserDTO> userDTOS = users.stream()
                .map(AutoUserMapper.MAPPER::mapToDTO)
                .collect(Collectors.toList());
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(userDTOS);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());
        return userResponse;
    }

    // Using WebClient and RestTemplate are similar we need to add the dependencies and create a Bean and implement in the
    // service Implementation class

    //Using feign client
    // add feign client dependency to class
    // enable feign client with annotation @EnableFeignClients
    // create Feign client and implement

//    @CircuitBreaker(name= "${spring.application.name}" , fallbackMethod = "getDefaultOrders")
    @Retry(name= "${spring.application.name}" , fallbackMethod = "getDefaultOrders")
    @Override
    public UserDtoOrders getUsersOrders(Long userId) {
        logger.info("In the GetUsersOrders");
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));

//        ResponseEntity<List<OrderDTO>> responseEntity = restTemplate.exchange(
//                "http://localhost:8082/api/orders/user/" + userId,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        List<OrderDTO> orderDTOS = webClient.get()
//                .uri("http://localhost:8082/api/orders/user/" + userId)
//                .retrieve()
//                .bodyToFlux(OrderDTO.class)
//                .collectList()
//                .block();

        List<OrderDTO> orders = feignAPIClient.getOrdersByUserId(userId);
        logger.info("Inside getOrderByUserId");

//        orders.forEach( order -> {
//            List<ProductDTO> products = webClient.get()
//                    .uri("http://localhost:8081/api/products/order/" + order.getOrderId())
//                    .retrieve()
//                    .bodyToFlux(ProductDTO.class)
//                    .collectList()
//                    .block();
//            order.setProductsList(products);
//        });

        UserDtoOrders userDtoOrders = new UserDtoOrders();
            userDtoOrders.setUserDTO(AutoUserMapper.MAPPER.mapToDTO(user));
            userDtoOrders.setOrderDTOS(orders);
          return userDtoOrders;

//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            UserDtoOrders userDtoOrders = new UserDtoOrders();
//            userDtoOrders.setUserDTO(AutoUserMapper.MAPPER.mapToDTO(user));
//            userDtoOrders.setOrderDTOS(responseEntity.getBody());
//            return userDtoOrders;
//        } else {
//            // Handle error response
//            throw new RuntimeException("Failed to fetch orders for user: " + userId);
//        }
    }

    @Override
    @CachePut(value = "getUserById", key = "#id")
    public UserDTO updateUserById(Long userId, User user) {
        logger.info("entered into update user by id method");
        User u = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));

         return AutoUserMapper.MAPPER.mapToDTO(user);
    }

//    @Override
//    public String verify(User user) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(user.getEmail());
//        } else {
//            return "fail";
//        }
//    };

    public UserDtoOrders getDefaultOrders(String userId, Exception e) {

        logger.info("In the GetDefaultOrders");
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new UserNotFoundException("No User Found with this userId: "+userId));


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(0);
        orderDTO.setUserId(0);
        orderDTO.setOrderStatus("Nothing");

        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderDTOS.add(orderDTO);

        UserDtoOrders userDtoOrders = new UserDtoOrders();
        userDtoOrders.setUserDTO(AutoUserMapper.MAPPER.mapToDTO(user));
        userDtoOrders.setOrderDTOS(orderDTOS);
        return userDtoOrders;

    }

    // Fetch rollout percentage for the feature
    @Override
    public boolean isFeatureEnabled(Long userId, Long weblabId) {
        WeblabDTO feature = feignWeblabClient.getWeblabById(weblabId);
        System.out.println("WEBLAB PERCENTAGE"+feature.getWeblabPercentage());
        if ( feature.getWeblabId() > 0 ) {
            long rolloutPercentage = feature.getWeblabPercentage();
            return assignUserToNewFeature(userId, rolloutPercentage);
        }
        return false;
    }


    public boolean assignUserToNewFeature(Long userId, Long rolloutPercentage) {
        int userHash = Math.abs(userId.hashCode()) % 100;
        System.out.println("USER HASH VALVE "+userHash);
        return userHash <= rolloutPercentage;
    }

}
