package rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class LoginResponse {

    @Length(max = 3)
    private String content;

    public LoginResponse() {
        // Jackson deserialization
    }

    public LoginResponse(String content) {
        this.content = content;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
