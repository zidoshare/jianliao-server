package site.zido.jianliao.entities;

import org.hibernate.annotations.GenericGenerator;
import site.zido.jianliao.tools.testToolbox.NoInjectValue;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zido on 17-5-3.
 * <p>
 * Date:17-5-3 上午11:58
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@Entity
public class UserGroup {
    @Id
    @GeneratedValue(generator = "myIdStrategy")
    @GenericGenerator(name = "myIdStrategy", strategy = "site.zido.jianliao.common.TimeId")
    private Long id;
    private String name = "我的好友"; //姓名
    @ManyToOne
    @NoInjectValue
    private SysUser originUser; //所属用户
    @ManyToMany
    @NoInjectValue
    private List<SysUser> users; //用户

    public String getName() {
        return name;
    }

    public UserGroup setName(String name) {
        this.name = name;
        return this;
    }

    public SysUser getOriginUser() {
        return originUser;
    }

    public UserGroup setOriginUser(SysUser originUser) {
        this.originUser = originUser;
        return this;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public UserGroup setUsers(List<SysUser> users) {
        this.users = users;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserGroup setId(Long id) {
        this.id = id;
        return this;
    }
}
