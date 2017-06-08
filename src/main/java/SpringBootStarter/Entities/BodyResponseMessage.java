package SpringBootStarter.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmad on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class BodyResponseMessage extends BodyResponse{
    private String chatName;
    private String sender;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BodyResponseMessage)) return false;

        BodyResponseMessage that = (BodyResponseMessage) o;

        if (timestamp != that.timestamp) return false;
        if (chatName != null ? !chatName.equals(that.chatName) : that.chatName != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = chatName != null ? chatName.hashCode() : 0;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    public String getChatName() {

        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BodyResponseMessage(String chatName, String sender, String content, long timestamp) {

        this.chatName = chatName;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    private long timestamp;

    public BodyResponseMessage() {
    }



    @Override
    public String toString() {
        return "BodyResponseMessage{" +
                "chatName='" + chatName + '\'' +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
