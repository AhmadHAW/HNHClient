package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class HeadAnmeldung {
    private String route;
    private String method;

    public HeadAnmeldung(String route, String method) {
        this.route = route;
        this.method = method;
    }

    public String getRoute() {

        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HeadAnmeldung() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeadAnmeldung head = (HeadAnmeldung) o;

        if (route != null ? !route.equals(head.route) : head.route != null) return false;
        return method != null ? method.equals(head.method) : head.method == null;
    }

    @Override
    public int hashCode() {
        int result = route != null ? route.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
}
