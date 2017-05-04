package site.zido.jianliao.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.zido.jianliao.dto.AjaxResult;
import site.zido.jianliao.entities.SysRole;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.RoleRepository;
import site.zido.jianliao.repository.UserRepository;
import site.zido.jianliao.tools.utils.toolbox.CollectionUtil;
import site.zido.jianliao.tools.utils.toolbox.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by zido on 17-5-4.
 * <p>
 * Date:17-5-4 下午5:41
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/pub",method = RequestMethod.POST)
public class PubController extends BaseController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @RequestMapping("/register")
    public AjaxResult register(String username,String nickname,String password){
        if(StringUtil.isBlank(username)){
            return fail("账号不能为空");
        }
        if(StringUtil.isBlank(nickname)){
            return fail("昵称不能为空");
        }
        if(StringUtil.isBlank(password)){
            return fail("密码不能为空");
        }
        SysRole role = roleRepository.findByName("用户");
        SysUser user = new SysUser().setUsername(username).setNickname(nickname).setPassword(password).setRoles(CollectionUtil.newArrayList(role));
        userRepository.save(user);
        return success("注册成功");
    }
}
