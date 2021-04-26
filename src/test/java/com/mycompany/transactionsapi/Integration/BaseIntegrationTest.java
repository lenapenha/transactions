package com.mycompany.transactionsapi.Integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(
  locations = "/application-integrationtest.properties")
public class BaseIntegrationTest {
    
}
