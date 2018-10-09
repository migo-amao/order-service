package wei.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    private String id;

    public String getId() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In app config");
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
