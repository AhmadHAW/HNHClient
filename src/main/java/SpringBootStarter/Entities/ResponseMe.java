package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 07.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseMe extends Response{
    HeaderResponse header;

    public ResponseMe() {
    }

    public ResponseMe(HeaderResponse header, BodyResponseMe body) {

        this.header = header;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseMe{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseMe)) return false;

        ResponseMe that = (ResponseMe) o;

        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    public HeaderResponse getHeader() {

        return header;
    }

    public void setHeader(HeaderResponse header) {
        this.header = header;
    }

    public BodyResponseMe getBody() {
        return body;
    }

    public void setBody(BodyResponseMe body) {
        this.body = body;
    }

    BodyResponseMe body;
}
