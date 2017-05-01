package site.zido.jianliao.dto.broadcast;

/**
 * the message is the request message for webSocket.
 * <p>
 * Date:17-5-2 上午12:34
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class BroadcastMessage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
