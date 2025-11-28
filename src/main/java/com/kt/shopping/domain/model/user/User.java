package com.kt.shopping.domain.model.user;

import com.kt.shopping.constants.Gender;
import com.kt.shopping.constants.Role;
import com.kt.shopping.domain.model.common.BaseEntity;
import com.kt.shopping.domain.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "\"user\"")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public User(String loginId,
                String password,
                String name,
                String email,
                String mobile,
                Gender gender,
                LocalDate birthday,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                Role role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.birthday = birthday;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void update(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}
