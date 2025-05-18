package pt.mleiria.dt;

public class MessageResponse {
    private String responseMessage;
    private ResponseResult responseResult;

    public MessageResponse(String responseMessage, ResponseResult responseResult) {
        this.responseMessage = responseMessage;
        this.responseResult = responseResult;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "responseMessage='" + responseMessage + '\'' +
                ", responseResult=" + responseResult +
                '}';
    }
}
