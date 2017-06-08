package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class HeadPost extends HeadAnmeldung {
    String userId;

    @Override
    public String toString() {
        return "HeadPost{" +
                "userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeadPost)) return false;
        if (!super.equals(o)) return false;

        HeadPost headPost = (HeadPost) o;

        return userId != null ? userId.equals(headPost.userId) : headPost.userId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HeadPost(String route, String method, String userId) {
        super(route, method);
        this.userId = userId;
    }
}
