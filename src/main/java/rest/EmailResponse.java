package rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class EmailResponse {

    @Length(max = 3)
    private String content;

    public EmailResponse() {
        // Jackson deserialization
    }

    public EmailResponse(String content) {
        this.content = content;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
