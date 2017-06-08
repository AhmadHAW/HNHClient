package SpringBootStarter.Entities;

/**
 * Created by Ahmad on 05.06.2017.
 */
public class Request {
    private HeadAnmeldung header;

    public Request(HeadAnmeldung header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return header != null ? header.equals(request.header) : request.header == null;
    }

    @Override
    public int hashCode() {
        return header != null ? header.hashCode() : 0;
    }

    public HeadAnmeldung getHeader() {

        return header;
    }

    public void setHeader(HeadAnmeldung header) {
        this.header = header;
    }
}
