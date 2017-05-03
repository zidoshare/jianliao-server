package site.zido.jianliao.common;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * Created by zido on 17-5-3.
 * <p>
 * Date:17-5-3 下午1:17
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "spring.jpa")
public class MyStrategy extends SpringPhysicalNamingStrategy {
    private String prefix;
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        return applyPrefix(identifier,jdbcEnvironment);
    }

    /**
     * add a prefix,u can define this property 'spring.jpa.prefix' in application.properties
     * @param name identifier
     * @param jdbcEnvironment jdbcEnvironment
     * @return identifier
     */
    private Identifier applyPrefix(Identifier name, JdbcEnvironment jdbcEnvironment){
        StringBuilder builder = new StringBuilder(name.getText());
        if(prefix == null)
            prefix = "j";
        builder.insert(0, prefix+"_");
        return getIdentifier(builder.toString(),name.isQuoted(),jdbcEnvironment);
    }

    public String getPrefix() {
        return prefix;
    }

    public MyStrategy setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
