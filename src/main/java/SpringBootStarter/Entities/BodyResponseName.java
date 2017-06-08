package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class BodyResponseName extends BodyResponse{
    private String name;

    public BodyResponseName() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyResponseName that = (BodyResponseName) o;

        return name != null ? name.equals(this.name) : this.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyResponseName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return "BodyResponseName{" +
                "name='" + name + '\'' +
                '}';
    }
}
