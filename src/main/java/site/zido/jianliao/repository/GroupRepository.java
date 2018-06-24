package site.zido.jianliao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.zido.jianliao.entities.UserGroup;
import site.zido.jianliao.entities.SysUser;

import java.util.List;

/**
 * Created by zido on 17-5-3.
 * <p>
 * Date:17-5-3 下午12:11
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public interface GroupRepository extends JpaRepository<UserGroup,Long>{
    public List<UserGroup> findAllByOriginUser(SysUser user);
}
