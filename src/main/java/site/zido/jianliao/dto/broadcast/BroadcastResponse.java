package site.zido.jianliao.dto.broadcast;

/**
 * the response for webSocket.
 * <p>
 * Date:17-5-2 上午12:35
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class BroadcastResponse {
    public String message;

    public BroadcastResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
