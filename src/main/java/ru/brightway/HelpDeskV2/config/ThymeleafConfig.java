package ru.brightway.HelpDeskV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * Thymeleaf Configuration file
 */
@Configuration
public class ThymeleafConfig {
    /**
     * Бин для работы ролей доступа на странице в шаблоне Thymeleaf
     * @return Возвращает SpringSecurityDialect
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}
