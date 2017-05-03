package site.zido.jianliao.entities;

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
public class Group {
    @Id
    @GeneratedValue
    private Long Id;
    private String name = "我的好友"; //姓名
    @ManyToOne(fetch = FetchType.LAZY)
    @NoInjectValue
    private SysUser originUser; //所属用户
    @OneToMany
    @NoInjectValue
    private List<SysUser> users; //用户

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public SysUser getOriginUser() {
        return originUser;
    }

    public Group setOriginUser(SysUser originUser) {
        this.originUser = originUser;
        return this;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public Group setUsers(List<SysUser> users) {
        this.users = users;
        return this;
    }

    public Long getId() {
        return Id;
    }

    public Group setId(Long id) {
        Id = id;
        return this;
    }
}
