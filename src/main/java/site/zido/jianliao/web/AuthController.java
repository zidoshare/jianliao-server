package site.zido.jianliao.web;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import site.zido.jianliao.dto.AjaxResult;
import site.zido.jianliao.dto.WsMessage;
import site.zido.jianliao.entities.UserGroup;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.GroupRepository;
import site.zido.jianliao.repository.UserRepository;
import site.zido.jianliao.tools.utils.toolbox.CollectionUtil;
import site.zido.jianliao.tools.utils.toolbox.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * the controller for auth.
 * <p>
 * Date:17-5-2 下午9:44
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/user",method = RequestMethod.POST)
public class AuthController extends BaseController {
    @Resource
    private SimpMessageSendingOperations simpMessageSendingOperations;
    @Resource
    private GroupRepository groupRepository;
    @Resource
    private UserRepository userRepository;
    @RequestMapping("/now")
    public AjaxResult getUser(){
        SysUser user = getCurrentUser();
        if(user == null)
            return fail("未获取到用户数据");
        return successData(user);
    }

    /**
     * 用户上线,并将上线用户名推送给该用户的好友
     * @return 成功
     */
    @RequestMapping("/online")
    public AjaxResult online(@RequestParam(defaultValue = "1") int mode){
        SysUser user = getCurrentUser();
        List<UserGroup> userGroups = groupRepository.findAllByOriginUser(user);
        List<SysUser> friends = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            friends.addAll(userGroup.getUsers());
        }
        WsMessage message = new WsMessage()
                .setMessage(user.getUsername())
                .setType(mode == 1 ?WsMessage.TYPE_ONLINE:WsMessage.TYPE_OFFLINE);
        for (SysUser friend : friends) {
            simpMessageSendingOperations.convertAndSendToUser(friend.getUsername()+"","/queue/greetings", message);
        }
//        simpMessageSendingOperations.convertAndSendToUser(user.getUsername(),"/queue/greetings", message);
//        WsMessage message = new WsMessage()
//                .setMessage(user.getUsername())
//                .setType(mode == 1 ?WsMessage.TYPE_ONLINE:WsMessage.TYPE_OFFLINE);
//        simpMessageSendingOperations.convertAndSend("/queue/greetings",message);
        return success();
    }
    @RequestMapping("/changeName")
    public AjaxResult changeNickname(String name){
        SysUser user = getCurrentUser();
        userRepository.save(new SysUser().setId(user.getId()).setNickname(name));
        return success();
    }
    @RequestMapping("/update")
    public AjaxResult update(String username,String password,String nickname,String sign){
        SysUser currentUser = getCurrentUser();
        if(!StringUtil.isBlank(username))
            currentUser.setUsername(username);
        if(!StringUtil.isBlank(password))
            currentUser.setPassword(password);
        if(!StringUtil.isBlank(nickname))
            currentUser.setNickname(nickname);
        if(!StringUtil.isBlank(sign))
            currentUser.setSign(sign);
        userRepository.save(currentUser);
        WsMessage message = new WsMessage().setType("friend_update");
        if(!StringUtil.isAllBlank(nickname,sign)){
            List<UserGroup> userGroups = groupRepository.findAllByOriginUser(getCurrentUser());
            for (UserGroup userGroup : userGroups) {
                List<SysUser> users = userGroup.getUsers();

                for (SysUser user : users) {
                    simpMessageSendingOperations.convertAndSendToUser(user.getUsername(),"/queue/greetings",message);
                }
            }
        }
        simpMessageSendingOperations.convertAndSendToUser(getCurrentUser().getUsername(),"/queue/greetings",message);
        return success("修改信息成功");
    }
    @RequestMapping("/send")
    public AjaxResult chat(String content,String username){
        WsMessage message = new WsMessage().setType("chat").setMessage(content).setFromUser(getCurrentUser().getUsername());
        simpMessageSendingOperations.convertAndSendToUser(username,"/queue/chat",message);
        return successData(new WsMessage().setMessage(content));
    }

    @RequestMapping("/getUser")
    public AjaxResult getUserByUsername(String username){
        SysUser user = userRepository.findByUsername(username);
        if(user == null)
            return fail("未获取到用户信息");
        return successData(user);
    }
    @RequestMapping("/addFriend")
    public AjaxResult addFriend(String friendName,String groupName){
        List<SysUser> users = userRepository.findAllByNicknameOrUsername(friendName, friendName);
        if(users.size() == 0)
            return fail("添加失败,未找到该用户");
        SysUser currentUser = getCurrentUser();
        WsMessage message = new WsMessage().setFromUser(currentUser.getUsername()).setType(WsMessage.TYPE_ADD_USER);
        List<UserGroup> userGroups = groupRepository.findAllByOriginUser(currentUser);
        UserGroup userGroup = null;
        for (UserGroup g : userGroups) {
            if(Objects.equals(g.getName(), groupName)){
                 userGroup = g;
                 break;
            }
        }
        if(userGroup == null){
            userGroup = new UserGroup().setName(groupName).setOriginUser(currentUser);
            groupRepository.save(userGroup);
        }
        for (SysUser user : users) {
            if(Objects.equals(user.getId(), currentUser.getId()))
                return fail("你不能添加自己为好友");
            for (UserGroup g : userGroups) {
                List<SysUser> friends = g.getUsers();
                for (SysUser friend : friends) {
                    if(friend.getId().equals(user.getId())){
                        return fail("添加失败,你已经是该用户的好友了");
                    }
                }
            }
            List<UserGroup> friendUserGroups = groupRepository.findAllByOriginUser(user);
            if(CollectionUtil.isEmpty(friendUserGroups)){
                friendUserGroups.add(new UserGroup().setName("我的好友").setOriginUser(user));
                groupRepository.save(friendUserGroups);
            }
            UserGroup friendUserGroup = friendUserGroups.get(0);
            friendUserGroup.getUsers().add(currentUser);
            groupRepository.save(friendUserGroup);
            simpMessageSendingOperations.convertAndSendToUser(user.getUsername(),"/queue/greetings",message);
        }
        List<SysUser> friends = userGroup.getUsers();
        if(friends == null)
            friends = new ArrayList<>();
        friends.addAll(users);
        userGroup.setUsers(friends);
        groupRepository.save(userGroup);
        return success("添加成功");
    }
}
