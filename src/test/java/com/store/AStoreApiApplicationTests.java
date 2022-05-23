package com.store;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.store.mapper")
class AStoreApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
