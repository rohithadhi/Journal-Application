package com.example.journalApp;

import com.example.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class service {

    @Autowired
    private UserRepository userRespository;

    @Test
    public void testFindByUserName(){
        assertNotNull(userRespository.findByUserName("Rohith"));
    }
}
