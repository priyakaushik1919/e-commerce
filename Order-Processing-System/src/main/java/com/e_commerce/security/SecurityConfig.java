package com.e_commerce.security;

import com.e_commerce.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
@PropertySource(value = "classpath:security-config.yml", factory = YamlPropertySourceFactory.class)
public class SecurityConfig {

    private List<String> allowedPublicApis;
}
