package site.zido.jianliao.dto;

/**
 * Created by zido on 17-5-4.
 * <p>
 * Date:17-5-4 下午12:24
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class RequestMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public RequestMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
