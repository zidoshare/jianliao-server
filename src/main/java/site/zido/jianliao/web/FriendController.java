package site.zido.jianliao.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.zido.jianliao.common.security.LoggedInChecker;
import site.zido.jianliao.dto.AjaxResult;
import site.zido.jianliao.entities.UserGroup;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.GroupRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zido on 17-5-3.
 * <p>
 * Date:17-5-3 下午1:43
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/user/friends",method = RequestMethod.POST)
public class FriendController extends BaseController{
    @Resource
    private LoggedInChecker checker;
    @Resource
    private GroupRepository groupRepository;
    @RequestMapping("/index")
    public AjaxResult index(){
        SysUser user = checker.getLoggedInUser();
        List<UserGroup> userGroups = groupRepository.findAllByOriginUser(user);
        return successData(userGroups);
    }
}
