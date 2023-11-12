package com.neu.dreambuilder.service.volunteer.impl;

import com.neu.dreambuilder.service.volunteer.VolunteerArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class volunTest {
    @Autowired
    private VolunteerArticleServiceImpl volunteerArticleService;

    @Test
    void articleDetails(){
         volunteerArticleService.getArticleComments(1);


    }

}
