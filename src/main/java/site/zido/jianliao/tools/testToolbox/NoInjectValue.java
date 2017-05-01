package site.zido.jianliao.tools.testToolbox;

import java.lang.annotation.*;

/**
 * 在生成模拟实体的时候会读取此注解，如果有此注解的属性，将不会被注入模拟值.
 * Date: 2017/3/22 0022
 * Time: 3:19
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Target(ElementType.FIELD) //此注解只能放在属性上
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented//说明该注解将被包含在javadoc中
public @interface NoInjectValue {
}
