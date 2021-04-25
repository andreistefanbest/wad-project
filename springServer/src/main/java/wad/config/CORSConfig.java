package wad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(@Value("${angular.container.name}") String angularContainerName) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("Access-Control-Allow-Origin",
                                        "*",
                                        "Access-Control-Allow-Methods",
                                        "POST, GET, OPTIONS, PUT, DELETE",
                                        "Access-Control-Allow-Headers",
                                        "Origin, X-Requested-With, Content-Type, Accept")
//                        .allowedOrigins("http://" + angularContainerName + ":4200")
                        .allowedMethods("*");
            }
        };
    }
}
