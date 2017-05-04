package site.zido.jianliao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import site.zido.jianliao.dto.AjaxResult;
import site.zido.jianliao.exceptions.ServiceException;

import javax.servlet.http.HttpServletResponse;
/**
 * Created by zido on 17-5-4.
 * <p>
 * Date:17-5-4 上午1:25
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        logger.error("发生运行时异常:"+e.getMessage());
        return new AjaxResult(false, "服务器发生异常");
    }
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e,HttpServletResponse response){
        response.setStatus(e.getCode());
        logger.error(e.getMessage());
        return new AjaxResult(false,e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e,HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        logger.error("发生代码异常:"+e.getMessage());
        return new AjaxResult(false,e.getMessage());
    }
}
