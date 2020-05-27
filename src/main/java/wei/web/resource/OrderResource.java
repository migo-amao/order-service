package wei.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wei.config.AppConfig;
/*import wei.web.client.AccountService;
import wei.web.client.StockService;*/
import wei.web.domain.Account;
import wei.web.domain.Order;

@RestController
public class OrderResource {

    /*@Autowired
    private AccountService accountService;

    @Autowired
    private StockService stockService;
*/
    @PreAuthorize("hasAuthority('SCOPE_ORDER_READ')")
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = new Order();
        order.setId(id);
        return order;
    }

    @PreAuthorize("hasAuthority('SCOPE_ORDER_WRITE')")
    @PostMapping("/orders")
    public void createOrder(@RequestBody Order order) {
        try {
            Account account = new Account(); //accountService.findAccount("1");
            int qty = 1; //stockService.findStock("2");
            System.out.println("Account for order = " + account.getId() + ", qty = " + qty);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
