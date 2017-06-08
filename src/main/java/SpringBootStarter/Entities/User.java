package SpringBootStarter.Entities;

import SpringBootStarter.userInterfaceComponent.GCaV;
import SpringBootStarter.userInterfaceComponent.GivenObjectNotValidException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ahmad on 05.06.2017.
 */
public class User {
    public User(String userName) {
        this.userName = userName;
    }

    private String userName;
    private String userId;
    private Set<String> rooms = new HashSet<String>();


    public User(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return userId != null ? userId.equals(user.userId) : user.userId == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getRooms() {
        return rooms;
    }

    public void setRooms(Set<String> rooms) {
        this.rooms = rooms;
    }

    public boolean isValid() {
        return userName != null && GCaV.isValidName(userName) && userId != null;
    }

    public void addRoom(String roomName) {
        rooms.add(roomName);
    }

    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }
}
