package site.zido.jianliao.web.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import site.zido.jianliao.dto.broadcast.BroadcastMessage;
import site.zido.jianliao.dto.broadcast.BroadcastResponse;
import site.zido.jianliao.web.BaseController;

/**
 * Created by zido on 17-5-2.
 * <p>
 * Date:17-5-2 上午1:01
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@Controller
public class WsController extends BaseController{
    @MessageMapping("/welcome")
    @SendTo("/broadcast/response")
    public BroadcastResponse say(BroadcastMessage message){
        return new BroadcastResponse("welcome to jianliao "+message.getName());
    }
}
