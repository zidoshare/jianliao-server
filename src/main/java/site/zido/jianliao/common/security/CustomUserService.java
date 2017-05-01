package site.zido.jianliao.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(s);
    }
}
