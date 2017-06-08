package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class BodyResponseMe extends BodyResponse{
    public BodyResponseMe() {
    }

    String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyResponseMe that = (BodyResponseMe) o;

        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BodyResponseMe(String userId) {

        this.content = userId;
    }

    @Override
    public String toString() {
        return "BodyResponseMe{" +
                "content='" + content + '\'' +
                '}';
    }
}
