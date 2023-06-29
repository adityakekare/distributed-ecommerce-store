package com.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.model.Product;
import com.repository.ProductRepository;

@Configuration
class LoadDatabase {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Product("Hoodie", (float) 25.99)));
      log.info("Preloading " + repository.save(new Product("Shirt", (float) 10)));
      log.info("Preloading " + repository.save(new Product("T-Shirt", (float) 42)));
      log.info("Preloading " + repository.save(new Product("Pants", (float) 23.99)));
    };
  }
}