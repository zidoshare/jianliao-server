package site.zido.jianliao.tools;

import org.springframework.stereotype.Component;

/**
 * 字符串工具类.
 * Date: 2017/3/22 0022
 * Time: 3:32
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class StringTool {
    /**
     * 字符串首字母变大写
     * @param str 字符串
     * @return
     */
    public String captureName(String str) {
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
