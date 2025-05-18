package pt.mleiria.dt;

public class MessageRequest {
    private String message;

    // Default constructor is required for JSON deserialization
    public MessageRequest() {
    }

    public MessageRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
