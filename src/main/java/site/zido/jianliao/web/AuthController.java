package site.zido.jianliao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import site.zido.jianliao.common.security.LoggedInChecker;
import site.zido.jianliao.entities.SysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * the controller for auth.
 * <p>
 * Date:17-5-2 下午9:44
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Resource
    private LoggedInChecker checker;
    @RequestMapping("/now")
    public SysUser getUser(){
        return checker.getLoggedInUser();
    }
}
