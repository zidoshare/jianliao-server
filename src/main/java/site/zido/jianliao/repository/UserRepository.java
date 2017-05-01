package site.zido.jianliao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.zido.jianliao.entities.SysUser;

/**
 * cyd.
 * Date: 2017/3/22 0022
 * Time: 20:18
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
public interface UserRepository extends JpaRepository<SysUser,Long> {

    public SysUser getUserByUsername(String username);
}
