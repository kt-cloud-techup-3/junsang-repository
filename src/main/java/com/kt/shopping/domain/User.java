package com.kt.shopping.domain;


import com.kt.shopping.domain.dto.request.UserCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String name;
    private LocalDate birthday;

    public static User create(final UserCreateRequest request) {
        return new User(
                request.loginId(),
                request.password(),
                request.name(),
                request.birthday()
        );
    }

}
