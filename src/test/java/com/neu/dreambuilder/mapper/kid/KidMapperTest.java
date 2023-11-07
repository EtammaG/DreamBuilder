package com.neu.dreambuilder.mapper.kid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class KidMapperTest {

    @Autowired
    private KidMapper kidMapper;

    @Test
    void selectByIds() {
        System.out.println(kidMapper.selectByIds(new ArrayList<>() {{
            add(123L);
            add(432L);
        }}));
    }
}