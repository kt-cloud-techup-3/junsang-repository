package com.kt.shopping.repository;

import com.kt.shopping.common.api.CustomException;
import com.kt.shopping.common.api.ErrorCode;
import com.kt.shopping.domain.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByLoginId(String loginId);

    Page<User> findAllByNameContaining(String keyword, Pageable pageable);

    default User findByIdOrThrow(Long id, ErrorCode errorCode) {
        return findById(id).orElseThrow(() -> new CustomException(errorCode));
    }
}
