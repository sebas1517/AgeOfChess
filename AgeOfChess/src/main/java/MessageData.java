import java.io.Serializable;

public class MessageData implements Serializable {
    private String message;
    private String sendTo;

    public MessageData(String message, String sendTo){
        this.message = message;
        this.sendTo = sendTo;

    }

    public String getMessage(){
        return this.message;
    }


    public String getSendTo(){
        return this.sendTo;
    }
}
