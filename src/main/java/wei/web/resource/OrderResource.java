package wei.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wei.config.OrderConfig;
import wei.web.domain.Order;

@RestController
public class OrderResource {

    @Autowired
    private OrderConfig orderConfig;

    @GetMapping("/orders/{id}")
    public Order getOrder() {
        Order order = new Order();
        order.setDate(orderConfig.getDate());
        order.setId(orderConfig.getId());
        return order;
    }
}
