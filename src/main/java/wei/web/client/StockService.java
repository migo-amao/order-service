package wei.web.client;

/*
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service", fallback = StockServiceFallback.class)
public interface StockService {

    @GetMapping("/stocks/{id}")
    int findStock(@PathVariable("id") String id);
}

@Component
class StockServiceFallback implements StockService {
    @Override
    public int findStock(String id) {
        return -1;
    }
}*/
