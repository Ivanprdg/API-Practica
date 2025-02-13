package com.ivanprdg.mi_primer_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("com.ivanprdg") // 🔥 Escanea todos los componentes
@EnableJpaRepositories("com.ivanprdg.model.dao") // 🔥 Escanea los repositorios
@EntityScan("com.ivanprdg.model.entity") // 🔥 Escanea las entidades JPA
public class MiPrimerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiPrimerApiApplication.class, args);
    }
}
