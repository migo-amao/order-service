package wei.web.client;

import feign.FeignException;
import feign.Logger;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wei.web.domain.Account;

@FeignClient(name = "account-service", configuration = AccountServiceFeignClientConfiguration.class/*, fallbackFactory = AccountServiceFallbackFactory.class*//*, fallback = AccountServiceFallback.class*/)
public interface AccountService {

    @GetMapping("/accounts/{id}")
    Account findAccount(@PathVariable("id") String id) throws FeignException;
}


//@Component
class AccountServiceFallbackFactory implements FallbackFactory<AccountService> {
    @Override
    public AccountService create(Throwable throwable) {
        return new AccountService() {
            @Override
            public Account findAccount(String id) {
                System.out.println(throwable.getCause());
                Account account = new Account();
                account.setId("account service fall back factory");
                return account;
            }
        };
    }
}

//@Component
class AccountServiceFallback implements AccountService {
    @Override
    public Account findAccount(String id) {
        Account account = new Account();
        account.setId("account service fall back");
        return account;
    }
}

class AccountServiceFeignClientConfiguration {

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    /*@Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 2000, 2);
    }
*/
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

}