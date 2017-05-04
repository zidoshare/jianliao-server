package site.zido.jianliao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

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
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    private Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);
    /**
     * register endpoints
     * @param registry the registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //register two endpoint named "/broadcast" and "/chat" and use SockJs protocol
        //broadcast : public endpoint
        //queue : point to point
        registry.addEndpoint("/chat")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * set the message broker (消息代理)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
        //the address prefix of subscribing and the default option is '/user/' if there is not configuration
        registry.setUserDestinationPrefix("/user/");
        //set the global subscribe address
        registry.setApplicationDestinationPrefixes("/app/");
    }
}
