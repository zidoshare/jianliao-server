package site.zido.jianliao.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.UserRepository;

import javax.annotation.Resource;

/**
 * cyd.
 * Date: 2017/3/22 0022
 * Time: 20:17
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
public class CustomUserService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.getUserByUsername(s);
        logger.info("param = "+s);
        if (user == null)
            throw new UsernameNotFoundException("该账号不存在");
        return user;
    }
}
