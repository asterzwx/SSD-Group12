package hello;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin(origins = "https://gambit-team12.tk")
@EnableJpaRepositories(basePackages = "hello.repo")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/greeting-javaconfig").allowedOrigins("https://gambit-team12.tk");
//            }
//        };
//    }

}
