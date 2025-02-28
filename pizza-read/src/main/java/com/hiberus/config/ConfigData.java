package com.hiberus.config;

import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizzaRead;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(RepositorioPizzaRead repositorioPizzaRead) {
        return args -> {
            Pizza pizza1 = Pizza.builder()
                    .nombre("Napolitana")
                    .id(1)
                    .build();

            Pizza pizza2 = Pizza.builder()
                    .nombre("Pepperoni")
                    .id(2)
                    .build();

            Pizza pizza3 = Pizza.builder()
                    .nombre("Hawaiana")
                    .id(3)
                    .build();

            Pizza pizza4 = Pizza.builder()
                    .nombre("Fugazza")
                    .id(4)
                    .build();

            repositorioPizzaRead.saveAll(List.of(pizza1, pizza2, pizza3, pizza4));
        };
    }
}
