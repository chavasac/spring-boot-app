package com.chavasac.springboot.springsecurityjpa.security.service;

import com.chavasac.springboot.springsecurityjpa.repository.UserRepository;
import com.chavasac.springboot.springsecurityjpa.security.domain.ApplicationUserDetails;
import com.chavasac.springboot.springsecurityjpa.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(ApplicationUserDetails::new).get();
    }

    @PostConstruct
    public void initialize() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setRoles("USER");
        user1.setActive(true);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("pass2");
        user2.setRoles("ADMIN");
        user2.setActive(true);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
