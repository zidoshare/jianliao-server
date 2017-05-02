package site.zido.jianliao.dto.chat;

/**
 * Created by zido on 17-5-2.
 * <p>
 * Date:17-5-2 下午4:09
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class ChatMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public ChatMessage setContent(String content) {
        this.content = content;
        return this;
    }
}
