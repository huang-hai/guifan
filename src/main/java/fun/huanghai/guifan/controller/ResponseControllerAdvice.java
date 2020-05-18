package fun.huanghai.guifan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.huanghai.guifan.exception.APIException;
import fun.huanghai.guifan.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 注意哦，这里要加上需要扫描的包
@RestControllerAdvice(basePackages = {"fun.huanghai.guifan.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        //如果接口返回的类型本身就是ResultVO那就么有必要进行额外操作,直接返回false
        return !returnType.getGenericParameterType().equals(ResultVO.class);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类型不能直接包装,所以需要进行特别的处理
        if(returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                //将数据包装在ResultVO里后,在转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        //将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
