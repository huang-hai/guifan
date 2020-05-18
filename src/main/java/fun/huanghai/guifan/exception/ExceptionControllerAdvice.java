package fun.huanghai.guifan.exception;

import fun.huanghai.guifan.vo.ResultCode;
import fun.huanghai.guifan.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //从异常对象中拿到ObjectError对象
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        //然后提取错误提示信息进行返回
        //return error.getDefaultMessage();
        //注意这里返回类型是自定义响应体
        //return new ResultVO<String>(1001,"参数校验失败",error.getDefaultMessage());
        //注意这里传递的是响应码枚举
        return new ResultVO<String>(ResultCode.VALIDATE_FAILED,error.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e){
//        return new ResultVO<>(e.getCode(),"响应失败",e.getMsg());
        return new ResultVO<String>(ResultCode.FAILED,e.getMsg());
    }
}
