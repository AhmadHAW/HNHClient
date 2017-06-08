package SpringBootStarter.Entities;

/**
 * Created by Ahmad on 05.06.2017.
 */
public class RequestPost extends Request{

    private BodyPost body;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RequestPost that = (RequestPost) o;

        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    public BodyPost getBody() {

        return body;
    }

    public void setBody(BodyPost body) {
        this.body = body;
    }

    public RequestPost(HeadAnmeldung head, BodyPost body) {

        super(head);
        this.body = body;
    }
}
