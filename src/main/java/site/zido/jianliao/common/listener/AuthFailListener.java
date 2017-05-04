package site.zido.jianliao.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import site.zido.jianliao.exceptions.ServiceException;

/**
 * Created by zido on 17-5-4.
 * <p>
 * Date:17-5-4 上午1:52
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class AuthFailListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

    }
}
