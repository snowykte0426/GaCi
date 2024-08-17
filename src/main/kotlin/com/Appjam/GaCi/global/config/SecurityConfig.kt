package com.Appjam.GaCi.global.config

import com.Appjam.GaCi.domain.user.entity.Role
import com.Appjam.GaCi.global.auth.service.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }.headers { it.frameOptions { frameOptionsConfig -> frameOptionsConfig.disable() } }
            .authorizeHttpRequests { authorizeRequest ->
                authorizeRequest.requestMatchers("/posts/new", "/comments/save").hasRole(Role.USER.name)
                    .requestMatchers(
                        "/",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/login/*",
                        "/logout/*",
                        "/posts/**",
                        "/comments/**"
                    ).permitAll().anyRequest().authenticated()
            }.logout { it.logoutSuccessUrl("/") }.oauth2Login {
                it.defaultSuccessUrl("/home", true)
                it.failureUrl("/login?error")
                it.userInfoEndpoint { endpoint ->
                    endpoint.userService(customOAuth2UserService)
                }
            }

        return http.build()
    }
}