package site.zido.jianliao.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * 通用服务器错误,注意,此服务器错误会被捕捉,并将信息返回给前端.
 * <p>
 * Date:17-5-4 上午1:26
 *
 * @author <a href="zido.site">zido</a>
 * @version 1.0.0
 */
public class ServiceException extends Exception {
    private Integer code;
    public ServiceException(){

    }
    public ServiceException(String msg){
        super(msg);
        this.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public ServiceException(String msg,Integer code){
        super(msg);
        this.setCode(code);
    }

    public Integer getCode() {
        return code;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }
}
