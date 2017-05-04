package site.zido.jianliao.dto;

import site.zido.jianliao.tools.utils.toolbox.DateUtil;

import java.util.Date;

/**
 * Created by zido on 17-5-4.
 * <p>
 * Date:17-5-4 上午2:41
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class WsMessage {
    public static final String TYPE_ADD_USER = "add_user";
    public static final String TYPE_ONLINE = "online";
    public static final String TYPE_OFFLINE = "offline";
    private String type;
    private Integer toUserId;
    private Date time;
    private String formatDate;
    private String message;
    private String fromUser;

    public WsMessage() {
        setTime(new Date());
        setFormatDate(DateUtil.formatDateTime(getTime()));
    }

    public String getType() {
        return type;
    }

    public WsMessage setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public WsMessage setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public WsMessage setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public WsMessage setFormatDate(String formatDate) {
        this.formatDate = formatDate;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public WsMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getFromUser() {
        return fromUser;
    }

    public WsMessage setFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }
}
