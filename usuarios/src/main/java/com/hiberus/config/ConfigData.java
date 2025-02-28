package com.hiberus.config;

import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import io.swagger.models.auth.In;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ConfigData {
    @Bean("ConfigData")
    CommandLineRunner commandLineRunner(RepositorioUsuario repositorioUsuario) {
        return args -> {
            Set<Integer> pizzas1 = new LinkedHashSet<>();
            pizzas1.add(1);
            pizzas1.add(2);
            pizzas1.add(3);
            Usuario usuario1 =Usuario.builder()
                    .nombre("Emilio")
                    .pizzasFav(pizzas1)
                    .build();

            Set<Integer> pizzas2 = new LinkedHashSet<>();
            pizzas2.add(2);
            pizzas2.add(3);
            Usuario usuario2 =Usuario.builder()
                    .nombre("Lucía")
                    .pizzasFav(pizzas2)
                    .build();

            Set<Integer> pizzas3 = new LinkedHashSet<>();
            pizzas3.add(1);
            Usuario usuario3 =Usuario.builder()
                    .nombre("Matías")
                    .pizzasFav(pizzas3)
                    .build();

            repositorioUsuario.saveAll(List.of(usuario1, usuario2, usuario3));
        };
    }
}
