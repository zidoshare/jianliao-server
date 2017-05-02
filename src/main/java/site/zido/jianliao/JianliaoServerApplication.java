package site.zido.jianliao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.zido.jianliao.entities.SysRole;
import site.zido.jianliao.entities.SysUser;
import site.zido.jianliao.repository.RoleRepository;
import site.zido.jianliao.repository.UserRepository;
import site.zido.jianliao.tools.testToolbox.TestTool;
import site.zido.jianliao.tools.utils.toolbox.CollectionUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
public class JianliaoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JianliaoServerApplication.class, args);
    }
}
