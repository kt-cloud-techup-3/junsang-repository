package com.kt.shopping.service;

import com.kt.shopping.domain.User;
import com.kt.shopping.domain.dto.request.UserCreateRequest;
import com.kt.shopping.domain.dto.response.CustomPage;
import com.kt.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserCreateRequest request) {
        System.out.println(request.toString());
        User user = new User(
                userRepository.selectMaxId() + 1,
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
    public void changePassword(int id, String oldPassword, String password) {
        var user = userRepository.selectById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!user.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        userRepository.updatePassword(id, password);
    }

    @Override
    public User detail(Long id) {
        return userRepository.selectById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
    }

    @Override
    @Transactional
    public void update(Long id, String name, String email, String mobile) {
        userRepository.selectById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        userRepository.updateById(id, name, email, mobile);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public CustomPage search(int page, int size, String keyword) {
        var pair = userRepository.selectAll(page - 1, size, keyword);
        var pages = (int) Math.ceil((double) pair.getSecond() / size);

        return new CustomPage(
                pair.getFirst(),
                size,
                page,
                pages,
                pair.getSecond()
        );
    }
}
