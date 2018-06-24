package site.zido.jianliao.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.zido.jianliao.tools.testToolbox.NoInjectValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类.
 * Date: 2017/3/22 0022
 * Time: 19:37
 *
 * @author <a href="http://zido.site">zido</a>.
 * @version 1.0.0
 */
@Entity
public class SysUser implements UserDetails{
    @Id
    @GeneratedValue(generator = "myIdStrategy")
    @GenericGenerator(name = "myIdStrategy", strategy = "site.zido.jianliao.common.TimeId")
    //id
    private Long id;
    //username account
    private String username;
    //nickname show
    private String nickname;
    //password
    private String password;
    //sex 0-secrecy,1-male,2-female
    private Integer sex = 1;
    //person sign
    private String sign = "加入简聊的新成员";
    //avatar url
    private String imageUrl = "./images/avatar.gif";
    @ManyToMany(fetch=FetchType.EAGER) //即时查询,因为马上需要权限条件
    @NoInjectValue
    private List<SysRole> roles;
    @Column(nullable = false)
    @NoInjectValue
    private Integer enable = 1;

    private String skin = "./images/bg0.jpg";

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>
     * .
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return this.enable == 1;
    }

    public Long getId() {
        return id;
    }

    public SysUser setId(Long id) {
        this.id = id;
        return this;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public SysUser setRoles(List<SysRole> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getEnable() {
        return enable;
    }

    public SysUser setEnable(Integer enable) {
        this.enable = enable;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public SysUser setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public SysUser setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SysUser setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public SysUser setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getSkin() {
        return skin;
    }

    public SysUser setSkin(String skin) {
        this.skin = skin;
        return this;
    }
}
