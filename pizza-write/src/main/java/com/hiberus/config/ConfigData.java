package com.hiberus.config;

import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizzaWrite;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(RepositorioPizzaWrite repositorioPizzaWrite) {
        return args -> {
            Pizza pizza1 = Pizza.builder()
                    .nombre("Write-Napolitana")
                    .id(5)
                    .build();

            Pizza pizza2 = Pizza.builder()
                    .nombre("Write-Pepperoni")
                    .id(6)
                    .build();

            Pizza pizza3 = Pizza.builder()
                    .nombre("Write-Hawaiana")
                    .id(7)
                    .build();

            Pizza pizza4 = Pizza.builder()
                    .nombre("Write-Fugazza")
                    .id(8)
                    .build();

            repositorioPizzaWrite.saveAll(List.of(pizza1, pizza2, pizza3, pizza4));
        };
    }
}
