package site.zido.jianliao.tools;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误处理工具类.
 * Date: 2017/3/22 0022
 * Time: 19:05
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class ErrorTool {
    /**
     * 剥离校验结果，返回所有错误信息集合
     * @param result
     * @return
     */
    public List<String> getErrorMessages(BindingResult result){
        List<String> errors = new ArrayList<>();
        final List<ObjectError> allErrors = result.getAllErrors();
        for (ObjectError allError : allErrors) {
            errors.add(allError.getDefaultMessage());
        }
        return errors;
    }
}
