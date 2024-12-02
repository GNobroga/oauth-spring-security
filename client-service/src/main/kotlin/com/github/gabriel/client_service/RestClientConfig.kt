package com.github.gabriel.client_service

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(builder: RestClient.Builder, oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager): RestClient {
        // oAuth2AuthorizedClientManager - Permite requisitar o token no authorization server

        // Esse interceptor adiciona o token no cabeçalho de cada requisição sem precisar fazer manualmente igual fiz no ClientController.
        var requestInterceptor = OAuth2ClientHttpRequestInterceptor(oAuth2AuthorizedClientManager)
        requestInterceptor.setClientRegistrationIdResolver {
            "keycloak"
        }
        return builder
            .requestInterceptor(requestInterceptor)
            .build()
    }

}