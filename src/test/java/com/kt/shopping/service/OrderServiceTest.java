package com.kt.shopping.service;

import com.kt.shopping.constants.Gender;
import com.kt.shopping.constants.Role;
import com.kt.shopping.domain.model.product.Product;
import com.kt.shopping.domain.model.user.User;
import com.kt.shopping.repository.OrderProductRepository;
import com.kt.shopping.repository.OrderRepository;
import com.kt.shopping.repository.ProductRepository;
import com.kt.shopping.repository.UserRepository;
import com.kt.shopping.service.order.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderProductRepository.deleteAll();
    }

    @Test
    void 주문_생성() {
        // given
        var user = userRepository.save(
                new User(
                        "testuser",
                        "password",
                        "Test User",
                        "email",
                        "010-0000-0000",
                        Gender.MALE,
                        LocalDate.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        Role.USER
                )
        );
        Product product = Product.create(
                "테스트 상품명",
                100_000L,
                10L
        );
        productRepository.save(product);

        // when
        orderService.create(
                user.getId(),
                product.getId(),
                "수신자 이름",
                "수신자 주소",
                "010-1111-2222",
                2L
        );

        // then
        var foundedProduct = productRepository.findByIdOrThrow(product.getId());
        var foundedOrder = orderRepository.findAll().stream().findFirst();

        assertThat(foundedProduct.getStock()).isEqualTo(8L);
        assertThat(foundedOrder).isPresent();
    }

    @Test
    void 동시에_100명_주문() throws Exception {
        var repeatCount = 500;
        var userList = new ArrayList<User>();
        for (int i = 0; i < repeatCount; i++) {
            userList.add(new User(
                    "testuser-" + i,
                    "password",
                    "Test User-" + i,
                    "email-" + i,
                    "010-0000-000" + i,
                    Gender.MALE,
                    LocalDate.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    Role.USER
            ));
        }

        var users = userRepository.saveAll(userList);
        Product product = Product.create(
                "테스트 상품명",
                100_000L,
                10L
        );
        productRepository.save(product);

        productRepository.flush();

        // 동시에 주문해야하니까 쓰레드를 100개
        var executorService = Executors.newFixedThreadPool(100);
        var countDownLatch = new CountDownLatch(repeatCount);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        for (int i = 0; i < repeatCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    var targetUser = users.get(finalI);
                    orderService.create(
                            targetUser.getId(),
                            product.getId(),
                            targetUser.getName(),
                            "수신자 주소-" + finalI,
                            "010-1111-22" + finalI,
                            1L
                    );
                    successCount.incrementAndGet();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    failureCount.incrementAndGet();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        var foundedProduct = productRepository.findByIdOrThrow(product.getId());

        // 1번쓰레드에서 작업하다가 언락
        // 2번쓰레드에서 작업하다가 언락

        assertThat(successCount.get()).isEqualTo(10);
        assertThat(failureCount.get()).isEqualTo(490);
        assertThat(foundedProduct.getStock()).isEqualTo(0);
    }

}
