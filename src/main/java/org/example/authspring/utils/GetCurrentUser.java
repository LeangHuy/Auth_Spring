package org.example.authspring.utils;

import org.example.authspring.model.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class GetCurrentUser {
    //Get current user id
    public static UUID userId(){
        AppUser user= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserId();
    }
}
