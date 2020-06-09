package wei.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import wei.web.domain.Order;
import wei.web.domain.Payment;

@RestController
@RequestMapping("/order-service")
public class OrderResource {
    @Autowired
    @Qualifier("PaymentService")
    private WebClient paymentService;

    @PreAuthorize("hasAuthority('SCOPE_ORDER_READ')")
    @GetMapping("/orders/{id}")
    public Mono<Order> getOrder(@PathVariable String id, @AuthenticationPrincipal Authentication authenticationPrincipal) {
        Order order = new Order();
        order.setId(id);
        return Mono.just(order);
    }

    @PreAuthorize("hasAuthority('SCOPE_ORDER_CREATE')")
    @PostMapping("/orders")
    public Mono<Void> createOrder(@RequestBody Order order, @AuthenticationPrincipal Authentication authenticationPrincipal) {
        Jwt token = ((JwtAuthenticationToken) authenticationPrincipal).getToken();
        return paymentService
                .post()
                .uri("/payments")
                .body(BodyInserters.fromValue(new Payment()))
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + token.getTokenValue())
                .retrieve()
                .bodyToMono(Void.class);
    }
}
