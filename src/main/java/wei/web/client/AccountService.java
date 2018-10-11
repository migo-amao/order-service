package wei.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wei.web.domain.Account;

@FeignClient(name = "account-service", fallback = AccountServiceFallback.class)
public interface AccountService {

    @GetMapping("/accounts/{id}")
    Account findAccount(@PathVariable("id") String id);
}

@Component
class AccountServiceFallback implements AccountService {
    @Override
    public Account findAccount(String id) {
        Account account = new Account();
        account.setId("account service fall back");
        return account;
    }
}