package site.zido.jianliao.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Websocket configuration class.
 * <p>
 * Date:17-5-1 下午11:30
 *
 * @author <a href="http://zido.site">zido</a>
 * @version 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker //use STOMP protocol
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * register endpoints
     * @param registry the registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //register a endpoint named "/endpointBroadcast" and use SockJs protocol
        registry.addEndpoint("/endPointBroadcast").withSockJS();
    }

    /**
     * set the message broker (消息代理)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/broadcast");
    }
}
