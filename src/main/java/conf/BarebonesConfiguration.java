package conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class BarebonesConfiguration extends Configuration {


    @NotEmpty
    private String testing;

    @JsonProperty
    public String getTesting() {
        return testing;
    }


}