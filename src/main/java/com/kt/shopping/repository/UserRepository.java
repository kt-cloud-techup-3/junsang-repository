package com.kt.shopping.repository;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO MEMBER(login_id, password, name, birthday) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(
                sql,
                user.getId(),
                user.getPassword(),
                user.getName(),
                user.getBirthday()
        );

    }

    public void update(String userId, UserUpdateRequest update) {
        String sql = "UPDATE MEMBER SET password=?, name=?, birthday=? WHERE login_id=?";
        jdbcTemplate.update(
                sql,
                update.password(),
                update.name(),
                update.birthday(),
                userId
        );
    }
}
