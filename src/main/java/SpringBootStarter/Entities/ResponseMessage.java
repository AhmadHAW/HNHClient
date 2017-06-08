package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 07.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseMessage extends Response{
    HeaderResponse header;
    BodyResponseMessage body;

    public ResponseMessage() {
    }

    public ResponseMessage(HeaderResponse header, BodyResponseMessage body) {

        this.header = header;
        this.body = body;
    }

    public HeaderResponse getHeader() {

        return header;
    }

    public void setHeader(HeaderResponse header) {
        this.header = header;
    }

    public BodyResponseMessage getBody() {
        return body;
    }

    public void setBody(BodyResponseMessage body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseMessage)) return false;

        ResponseMessage that = (ResponseMessage) o;

        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
