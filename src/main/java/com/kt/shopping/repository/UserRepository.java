package com.kt.shopping.repository;

import com.kt.shopping.constants.user.Gender;
import com.kt.shopping.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {

        var sql = """
			INSERT 
              INTO 
			MEMBER (
					id, 
					loginId, 
					password, 
					name, 
					birthday,
					mobile,
					email,
					gender,
					createdAt,
					updatedAt
				) 
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";

        jdbcTemplate.update(
                sql,
                user.getId(),
                user.getLoginId(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getMobile(),
                user.getEmail(),
                user.getGender().name(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

    }

    public void updatePassword(long id, String password) {
        String sql = "UPDATE MEMBER SET password = ? WHERE id = ?";
        jdbcTemplate.update(sql, password, id);
    }

    public Long selectMaxId() {
        var sql = "SELECT MAX(id) FROM MEMBER";

        var maxId = jdbcTemplate.queryForObject(sql, Long.class);
        return maxId == null ? 0L : maxId;
    }

    public boolean existsByLoginId(String loginId) {
        String sql = "SELECT EXISTS (SELECT id FROM MEMBER WHERE loginId = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, loginId));
    }

    public Optional<User> selectById(long id) {
        String sql = "SELECT * FROM MEMBER WHERE id = ?";
        List<User> list = jdbcTemplate.query(sql, rowMapper(), id);
        return list.stream().findFirst();
    }

    private RowMapper<User> rowMapper() {
        return (rs, rowNum) -> mapToUser(rs);
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("loginId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                Gender.valueOf(rs.getString("gender")),
                rs.getObject("birthday", LocalDate.class),
                rs.getObject("createdAt", LocalDateTime.class),
                rs.getObject("updatedAt", LocalDateTime.class)
        );
    }



}
