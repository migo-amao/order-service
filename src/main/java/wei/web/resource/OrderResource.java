package wei.web.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wei.web.domain.Account;
import wei.web.domain.Order;

@RestController
@RequestMapping("/order-service")
public class OrderResource {

    @PreAuthorize("hasAuthority('SCOPE_ORDER_READ')")
    @GetMapping("/orders/{id}")
    public Mono<Order> getOrder(@PathVariable String id, @AuthenticationPrincipal Authentication authenticationPrincipal) {
        // authentication will be null in reactive mode since the thread local that holds the security context doesn't apply
        // in the reactive mode
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = new Order();
        order.setId(id);
        return Mono.just(order);
    }

    @PreAuthorize("hasAuthority('SCOPE_ORDER_WRITE')")
    @PostMapping("/orders")
    public void createOrder(@RequestBody Order order) {
        try {
            Account account = new Account();
            int qty = 1;
            System.out.println("Account for order = " + account.getId() + ", qty = " + qty);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
