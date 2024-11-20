package cs544.dto;

import java.io.Serializable;

public class UserEvent implements Serializable {
    private String eventType; // CREATE, UPDATE, DELETE
    private Long userId;
    private UserDto userData; // Include details for CREATE/UPDATE
    private long timestamp;

    public UserEvent() {
    }

    public UserEvent(String eventType, Long userId, UserDto userData, long timestamp) {
        this.eventType = eventType;
        this.userId = userId;
        this.userData = userData;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "eventType='" + eventType + '\'' +
                ", userId=" + userId +
                ", userData=" + userData +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto getUserData() {
        return userData;
    }

    public void setUserData(UserDto userData) {
        this.userData = userData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
