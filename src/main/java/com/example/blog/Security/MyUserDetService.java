package com.example.blog.Security;

import com.example.blog.Components.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MyUserDetService implements UserDetailsService {

    final
    MainModel mainModel;

    @Autowired
    public MyUserDetService(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("############START_LOGIN##########");
        System.out.println("USERNAME: " + username);

        Map<String,Object> currentUser = mainModel.findUsr(username);

        System.out.println("#########USER WAS FIND#########");

        String login = String.valueOf(currentUser.get("login"));
        String password = String.valueOf(currentUser.get("pass"));
        String role = String.valueOf(currentUser.get("role"));

        System.out.println("User LOGIN: " + login);
        System.out.println("User PASSWORD: " + password);
        System.out.println("User ROLE: " + role);

        return User.builder()
                .username(login)
                .password(password)
                .roles(role)
                .build();
    }
}
