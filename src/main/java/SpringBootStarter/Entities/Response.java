package SpringBootStarter.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {

    HeaderResponse header;
    BodyResponse body;

    @Override
    public String toString() {
        return "Response{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;

        Response response = (Response) o;

        if (header != null ? !header.equals(response.header) : response.header != null) return false;
        return body != null ? body.equals(response.body) : response.body == null;
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

    public BodyResponse getBody() {
        return body;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }

    public Response(HeaderResponse header, BodyResponse body) {

        this.header = header;
        this.body = body;
    }

    public Response() {

    }
}
