package resources;


import com.yammer.metrics.annotation.Timed;
import models.Credentials;
import rest.LoginResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private final AtomicLong counter;

    public LoginResource() {
        this.counter = new AtomicLong();
    }

    @POST
    @Timed
    public LoginResponse sayHello(Credentials credentials) throws IOException {


        return new LoginResponse("success");
    }
}