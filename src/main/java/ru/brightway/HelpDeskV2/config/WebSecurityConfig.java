package ru.brightway.HelpDeskV2.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.brightway.HelpDeskV2.services.DefaultUserService;

/**
 * Параметры защищенных зон приложения, аутентификации и http
 */
@SuppressWarnings("Lombok")
@EnableWebSecurity
@Data
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Инъекция сервиса пользователей
     */
    @Autowired
    private DefaultUserService userService;

    /**
     * Бин для хеширования паролей
     * @return Шифровальщик для паролей
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Конфигурация HTTP
     * Защищенными областями приложения являются все области.
     * С приложением может работать только авторизованный пользователь
     * @param httpSecurity На вход. Стандартное исполнение
     * @throws Exception стандартное исполнение обработки исключений
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()

                .antMatchers("/workplace/**").authenticated()
                .antMatchers("/support/**").hasRole("SUPPORT")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                ;
    }

    /**
     * Бин создания Authentication Provider
     * Стандартное исполнение для базы данных.
     * Используются пользователи, хранящиеся в базе данных.
     * @return Провайдер аутентификации
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
