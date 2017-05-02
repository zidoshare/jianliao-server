package site.zido.jianliao.common;

import org.springframework.context.annotation.Configuration;
import site.zido.jianliao.entities.SysRole;
import site.zido.jianliao.entities.SysUser;
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
public class CommonConfiguration {
    @Resource
    private TestTool testTool;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    @PostConstruct
    public void initialData() {
        List<SysRole> roles = testTool.generateEntities(
                new SysRole().setName("管理员"),
                new SysRole().setName("超级管理员"),
                new SysRole().setName("用户"));
        roleRepository.save(roles);
        List<SysUser> users = testTool.generateEntities(
                new SysUser().setUsername("aaa").setPassword("aaa").setRoles(CollectionUtil.newArrayList(roles.get(2))),
                new SysUser().setUsername("bbb").setPassword("bbb").setRoles(CollectionUtil.newArrayList(roles.get(2))));
        userRepository.save(users);
    }
}
