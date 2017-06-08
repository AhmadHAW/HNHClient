package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class BodyResponse {
    public BodyResponse() {
    }

    @Override
    public String toString() {
        return "BodyResponse{}";
    }
}
