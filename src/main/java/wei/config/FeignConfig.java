package wei.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //@Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 2000, 2);
    }

}
