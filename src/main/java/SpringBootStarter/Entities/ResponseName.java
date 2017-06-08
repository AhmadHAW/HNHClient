package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Ahmad on 07.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseName extends Response{
    HeaderResponse header;
    BodyResponseName body;

    public ResponseName() {
    }

    public ResponseName(HeaderResponse header, BodyResponseName body) {

        this.header = header;
        this.body = body;
    }

    public HeaderResponse getHeader() {

        return header;
    }

    public void setHeader(HeaderResponse header) {
        this.header = header;
    }

    public BodyResponseName getBody() {
        return body;
    }

    public void setBody(BodyResponseName body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof ResponseName)) return false;

        ResponseName that = (ResponseName) o;

        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseName{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
