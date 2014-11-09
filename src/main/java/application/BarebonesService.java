package application;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import conf.BarebonesConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resources.EmailResource;
import resources.LoginResource;

import javax.ws.rs.core.MediaType;
import java.util.Map;

public class BarebonesService extends Service<BarebonesConfiguration> {

  public static void main(String[] args) throws Exception {
    new BarebonesService().run(args);
  }

  @Override
  public void initialize(Bootstrap<BarebonesConfiguration> bootstrap) {
  }

  @Override
  public void run(BarebonesConfiguration config, Environment env) throws Exception {
    Map<String, MediaType> mediaTypeMap = env.getJerseyResourceConfig().getMediaTypeMappings();
    mediaTypeMap.put("json", MediaType.APPLICATION_JSON_TYPE);
    mediaTypeMap.put("xml", MediaType.APPLICATION_XML_TYPE);

      env.addFilter(CrossOriginFilter.class, "/*")
              .setInitParam("allowedOrigins", "*")
              .setInitParam("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin")
              .setInitParam("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    if (config.getTesting() != null && config.getTesting().equals("true")) {
      //turn on json pretty printing
      env.getObjectMapperFactory().enable(SerializationFeature.INDENT_OUTPUT);
    }

    env.addResource(new LoginResource());
    env.addResource(new EmailResource());
  }
}
