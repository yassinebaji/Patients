package com.enset.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User Custumuser = userRepository.findByUsername(s);
        if (Custumuser == null) {
            throw new UsernameNotFoundException(s);
        }
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(Custumuser.getUsername()).password(Custumuser.getPassword())
                .authorities("ADMIN").build();
        return user;
    }
}
