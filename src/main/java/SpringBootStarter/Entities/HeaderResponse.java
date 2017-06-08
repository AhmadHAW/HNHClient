package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeaderResponse {
    private int code;
    private String description;
    private String type;

    public HeaderResponse() {
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeaderResponse that = (HeaderResponse) o;

        if (code != that.code) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HeaderResponse(int code, String description, String type) {

        this.code = code;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "HeaderResponse{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
