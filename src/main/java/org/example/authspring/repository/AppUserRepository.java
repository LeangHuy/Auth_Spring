package org.example.authspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.authspring.model.dto.request.AppUserRequest;
import org.example.authspring.model.entity.AppUser;


@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "id"),
    })
    @Select("SELECT * FROM users WHERE email = #{email}")
    AppUser findByEmail(String email);


    @ResultMap("appUserMapper")
    @Select("INSERT INTO users (email, password) VALUES (#{appUser.email}, #{appUser.password}) RETURNING *")
    AppUser register(@Param("appUser") AppUserRequest appUserRequest);

    @Update("UPDATE users SET password = #{password} WHERE email = #{email}")
    void forget(String email, String password);
}
