package site.zido.jianliao.common;

import org.springframework.context.annotation.Configuration;
import site.zido.jianliao.entities.Group;
import site.zido.jianliao.entities.SysRole;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.GroupRepository;
import site.zido.jianliao.repository.RoleRepository;
import site.zido.jianliao.repository.UserRepository;
import site.zido.jianliao.tools.testToolbox.TestTool;
import site.zido.jianliao.tools.utils.toolbox.CollectionUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zido on 17-5-2.
 * <p>
 * Date:17-5-2 下午10:46
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@Configuration
public class InitialConfiguration {
    @Resource
    private TestTool testTool;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private GroupRepository groupRepository;

    @PostConstruct
    public void initialData() {
        List<SysRole> roles = testTool.generateEntities(
                new SysRole().setName("管理员"),
                new SysRole().setName("超级管理员"),
                new SysRole().setName("用户"));
        roleRepository.save(roles);
        List<SysUser> users = testTool.generateEntities(
                new SysUser().setUsername("aaa").setPassword("aaa").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("bbb").setPassword("bbb").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("ddd").setPassword("ddd").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("eee").setPassword("eee").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("fff").setPassword("fff").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("ccc").setPassword("ccc").setRoles(CollectionUtil.newArrayList(roles.get(2))));
        userRepository.save(users);
        List<Group> groups = testTool.generateEntities(
                new Group().setName("我的好友").setOriginUser(users.get(0)).setUsers(CollectionUtil.sub(users, 1, 2)),
                new Group().setName("我的同学").setOriginUser(users.get(0)).setUsers(CollectionUtil.sub(users, 2, 3)),
                new Group().setName("公司").setOriginUser(users.get(0)).setUsers(CollectionUtil.sub(users, 3, 4)),
                new Group().setName("软件开发").setOriginUser(users.get(0)).setUsers(CollectionUtil.sub(users, 4, 5)),
                new Group().setName("维修核潜艇").setOriginUser(users.get(0)).setUsers(CollectionUtil.sub(users, 5, CollectionUtil.MAX_SIZE)),
                new Group().setName("核弹头抛光").setOriginUser(users.get(1)).setUsers(CollectionUtil.sub(users, 0, 1)),
                new Group().setName("航母上蜡").setOriginUser(users.get(1)).setUsers(CollectionUtil.sub(users, 2, 4)),
                new Group().setName("维修核潜艇").setOriginUser(users.get(1)).setUsers(CollectionUtil.sub(users, 4, CollectionUtil.MAX_SIZE)),
                new Group().setName("我的好友").setOriginUser(users.get(2)).setUsers(CollectionUtil.sub(users, 0, 2)),
                new Group().setName("我的好友").setOriginUser(users.get(3)).setUsers(CollectionUtil.sub(users, 0, 2)),
                new Group().setName("我的好友").setOriginUser(users.get(4)).setUsers(CollectionUtil.sub(users, 0, 2)),
                new Group().setName("我的好友").setOriginUser(users.get(5)).setUsers(CollectionUtil.sub(users, 0, 2))
                );
        groupRepository.save(groups);
    }
}
