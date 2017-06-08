package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class BodyResponseList extends BodyResponse{

    private String[] chats;

    public BodyResponseList() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyResponseList that = (BodyResponseList) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(chats, that.chats);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(chats);
    }

    public String[] getChats() {

        return chats;
    }

    public void setChats(String[] chats) {
        this.chats = chats;
    }

    public BodyResponseList(String[] chats) {

        this.chats = chats;
    }

    @Override
    public String toString() {
        return "BodyResponseList{" +
                "chats=" + Arrays.toString(chats) +
                '}';
    }
}
