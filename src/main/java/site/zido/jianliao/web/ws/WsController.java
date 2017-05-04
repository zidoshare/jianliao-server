package site.zido.jianliao.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.zido.jianliao.dto.AjaxResult;
import site.zido.jianliao.dto.broadcast.BroadcastMessage;
import site.zido.jianliao.dto.broadcast.BroadcastResponse;
import site.zido.jianliao.dto.chat.ChatMessage;

import javax.annotation.Resource;

/**
 * Created by zido on 17-5-2.
 * <p>
 * Date:17-5-2 上午1:01
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@Controller
public class WsController{
    private Logger logger = LoggerFactory.getLogger(WsController.class);
    @MessageMapping("/hello")
    @SendToUser("/message")
    public ChatMessage say(BroadcastMessage message){
        logger.info(message.getName());
        simpMessageSendingOperations.convertAndSendToUser("1","/message",message.getName()+"上线啦~");
        return new ChatMessage().setContent(message.getName()+"-上线啦");
    }

    //inject SimpMessagingTemplate for point to point
    @Resource
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chatWith")
    public ChatMessage chatWith(ChatMessage message){
        logger.info(message.getContent());

        return message;
    }
}
