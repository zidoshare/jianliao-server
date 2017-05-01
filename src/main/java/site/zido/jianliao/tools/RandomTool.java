package site.zido.jianliao.tools;

import org.springframework.stereotype.Component;

/**
 * 随机生成工具.
 * Date: 2017/3/22 0022
 * Time: 2:48
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class RandomTool {
    /**
     * 生成随机int值
     *
     * @param length   int值的长度
     * @param lessThan 是否允许小于此长度
     * @return int值
     */
    public int randomInt(int length, boolean lessThan) throws Exception {
        if(length > 9){
            throw new Exception("不支持长度大于9的情况");
        }
        if(!lessThan){
            StringBuilder result = new StringBuilder("");
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    result.append(((int) (Math.random() * 9)) + 1);
                    continue;
                }
                result.append((int) (Math.random() * 10));
            }
            return Integer.parseInt(result.toString());
        }
        final int pow = (int)Math.pow(10, length);
        return (int) (Math.random()*pow);
    }
    /**
     * 生成随机String字符串
     *
     * @param length   String字符串的长度
     * @param lessThan 是否允许小于此长度
     * @return int值
     */
    public String randomString(int length,boolean lessThan){
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            if(lessThan && Math.random() > 0.5){
                continue;
            }
            char c = ' ';
            char or = (char) ((int) (Math.random() * 94) + c);
            result.append(or);
        }
        return result.toString();
    }
}
