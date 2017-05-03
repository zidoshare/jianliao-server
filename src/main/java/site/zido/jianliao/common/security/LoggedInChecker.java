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
        SysUser user = null;
        if(authentication != null){
            Object principal = authentication.getPrincipal();
            if(principal instanceof SysUser){
                user = (SysUser) principal;
            }
        }
        return user;
    }
}
