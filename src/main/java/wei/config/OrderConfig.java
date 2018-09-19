package wei.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@ConfigurationProperties(prefix = "order")
public class OrderConfig {
    private String id;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
