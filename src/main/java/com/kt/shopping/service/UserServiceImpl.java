package com.kt.shopping.service;

import com.kt.shopping.domain.dto.request.user.UserRequest;
import com.kt.shopping.domain.dto.response.UserResponse;
import com.kt.shopping.domain.model.user.User;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserRequest.Create request) {
        User user = new User(
                request.loginId(),
                request.password(),
                request.name(),
                request.email(),
                request.mobile(),
                request.gender(),
                request.birthday(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        userRepository.save(user);
    }

    @Override
    public boolean isDuplicateLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    @Override
    @Transactional
    public void changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        if (!user.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(newPassword);
    }
    @Override
    public User detail(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
    }

    @Override
    @Transactional
    public void update(Long id, String name, String email, String mobile) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        user.update(name, email, mobile);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserResponse.Search> search(Pageable pageable, String keyword) {
        Page<User> list = userRepository.findAllByNameContaining(keyword, pageable);
        return list.map(
                user -> new UserResponse.Search(
                        user.getId(),
                        user.getName(),
                        user.getCreatedAt()
                )
        );
    }
}
