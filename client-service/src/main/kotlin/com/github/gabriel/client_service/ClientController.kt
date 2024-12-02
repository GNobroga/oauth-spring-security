package com.github.gabriel.client_service

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import java.math.BigDecimal

data class CreateProductRequestDTO(
    val name: String,
    val price: BigDecimal,) {
}
@RestController
@RequestMapping("/client")
class ClientController(
    val oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager,
    val restClient: RestClient,
) {

    @GetMapping("get-products")
    fun getProducts(): Any? {
//       val accessToken = oAuth2AuthorizedClientManager.authorize(
//           OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
//               .principal("client-credentials")
//               .build()
//       )?.accessToken
//
//        val tokenValue = accessToken?.tokenValue

        restClient.post()
            .uri("http://localhost:8000/products")
            .contentType(MediaType.APPLICATION_JSON)
//            .header("Authorization", "Bearer $tokenValue")
            .body(listOf(CreateProductRequestDTO(name="Smartphone", price=BigDecimal.valueOf(100))))
            .retrieve()
            .toBodilessEntity()

        return restClient.get()
            .uri("http://localhost:8000/products")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//            .header("Authorization", "Bearer $tokenValue")
            .retrieve()
            .body(Object::class.java)
    }
}