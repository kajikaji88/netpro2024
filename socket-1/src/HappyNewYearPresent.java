import java.io.Serializable;
public class HappyNewYearPresent implements Serializable{
    String message;
    String content;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String present) {
        this.content = present;
    }

}