package com.manikanta.microservices.project.UserService.Controller;

import com.manikanta.microservices.project.UserService.DTO.UserDtoOrders;
import com.manikanta.microservices.project.UserService.DTO.UserResponse;
import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.Entity.User;
import com.manikanta.microservices.project.UserService.Service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "User REST API",
        description = "User REST API to implement the CRUD operations"
)
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    private final MeterRegistry meterRegistry;

    @Operation(
            summary = "Get ALL Users",
            description = "Get all the Users Found in the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Got All Users 200"
    )
    @GetMapping()
    public UserResponse getUsers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "pageSortBy", defaultValue = "userId", required = false) String pageSortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return userService.getUsers(pageNo,pageSize,pageSortBy,sortDir);

    }

    @PostMapping
    public void validate(@PathVariable("token") String string){

    }

//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//
//        return userService.verify(user);
//    }

    @Operation(
            summary = "Get User By Id",
            description = "Get the User By Id Found in the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Got User with id 200"
    )
    @GetMapping("{user-id}")
    public UserDTO getUserById(@PathVariable("user-id") Long id) throws Exception {
        System.out.println("Inside GetUserById Controller");
        meterRegistry.counter("getUsersById").increment();
        return userService.getUser(id);
    }


    @PatchMapping("/update/{user-id}")
    public UserDTO getUpdateById(@PathVariable("user-id") Long id, @RequestBody @Valid User user){
        return userService.updateUserById(id, user);
    }



    @Operation(
            summary = "Delete User",
            description = "Delete the User From the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Deleted the User 200"
    )
    @DeleteMapping("{user-id}")
    public void deleteUserById(@PathVariable("user-id") Long id) {
        userService.deleteUser(id);
    }

    @Operation(
            summary = "Add the User",
            description = "Add the User into the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Added the User 200"
    )
    @PostMapping("/create")
    public void addUser(@RequestBody @Valid User user){
        meterRegistry.counter("getUserAndOrderByUserId").increment();
        userService.addUser(user);
    }


    @GetMapping("orders/{user-id}")
    public UserDtoOrders getUserAndOrderByUserId(@PathVariable("user-id") Long id){
        System.out.println(id);
        meterRegistry.counter("USERS_getUserAndOrderByUserId").increment();
        return userService.getUsersOrders(id);
    }

}