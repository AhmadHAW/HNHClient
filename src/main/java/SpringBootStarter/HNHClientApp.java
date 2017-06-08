package SpringBootStarter;

import SpringBootStarter.Entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import SpringBootStarter.userInterfaceComponent.Terminal;


/**
 * Created by Ahmad on 05.06.2017.
 */
@SpringBootApplication
@EnableAsync

public class HNHClientApp  extends AsyncConfigurerSupport {
    public static void main(String args[]) throws Exception {

        SpringApplication.run(HNHClientApp.class, args);
    }

}
