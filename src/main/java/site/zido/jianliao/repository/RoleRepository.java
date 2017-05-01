package site.zido.jianliao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.zido.jianliao.entities.SysRole;

/**
 * cyd.
 * Date: 2017/3/23 0023
 * Time: 0:10
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
public interface RoleRepository extends JpaRepository<SysRole,Long> {
}
