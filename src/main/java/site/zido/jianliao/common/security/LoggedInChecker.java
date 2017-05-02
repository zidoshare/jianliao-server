package site.zido.jianliao.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import site.zido.jianliao.entities.SysUser;

/**
 * Created by zido on 17-5-3.
 * <p>
 * Date:17-5-3 上午2:15
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@Component
public class LoggedInChecker {
    public SysUser getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) return (SysUser) authentication.getPrincipal();
        return null;
    }
}
