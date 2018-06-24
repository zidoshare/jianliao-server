package site.zido.jianliao.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * cyd.
 * Date: 2017/3/22 0022
 * Time: 23:34
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Entity
public class SysRole {
    @Id
    @GeneratedValue(generator = "myIdStrategy")
    @GenericGenerator(name = "myIdStrategy", strategy = "site.zido.jianliao.common.TimeId")
    private Long id;
    @Column(nullable = false,length = 10)
    private String name;

    public Long getId() {
        return id;
    }

    public SysRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysRole setName(String name) {
        this.name = name;
        return this;
    }
}
