//package com.manikanta.microservices.project.UserService.Controller;
//
//import com.manikanta.microservices.project.UserService.Entity.User;
//import com.manikanta.microservices.project.UserService.Entity.UserPrincipal;
//import com.manikanta.microservices.project.UserService.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepo.findByEmail(email);
//        if (user == null) {
//            System.out.println("User Not Found");
//            throw new UsernameNotFoundException("user not found");
//        }
//
//        return new UserPrincipal(user);
//    }
//}
