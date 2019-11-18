package com.cpy.website.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Gavin on 17/2/16.
 */
public abstract class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static final ApiStatusCode OK = ApiStatusCode.OK;

    public <T> void validate(T vo) throws WebApiException, StatusCodeException {

        /**
         * 校验ApiRequest
         */
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(vo);
        for (ConstraintViolation<T> v : violations) {
            logger.info("参数校验违例: " + v.getPropertyPath().toString() + " " + v.getMessage());
        }
        if (!violations.isEmpty()) {
            ConstraintViolation<T> v = violations.iterator().next();
            throw new ParamValidateException(v.getPropertyPath().toString(), v.getMessage());
        }

        /**
         * 特殊校验
         */
        specialValidate(vo);
    }

    public <T> void specialValidate(T apiRequest) throws ParamValidateException{
    }

    protected ApiResponse wrapApiResponse(ApiStatusCode code, String body, String msg) {
        return wrapApiResponse(code.value(), body, msg);
    }

    protected ApiResponse wrapApiResponse(ApiStatusCode code, String body, String msg, String traceMsg) {
        return wrapApiResponse(code.value(), body, msg, traceMsg);
    }

    protected <T> ApiResponse<T> wrapApiResponse(ApiStatusCode code, T body, String msg) {
        return wrapApiResponse(code.value(), body, msg);
    }

    protected <T> ApiResponse<T> wrapSuccessApiResponse(T body) {
        return wrapApiResponse(ApiStatusCode.OK, body, null);
    }

    protected <T> ApiResponse<T> success(T body) {
        return wrapApiResponse(ApiStatusCode.OK, body, null);
    }

    protected ApiResponse wrapApiResponse(int code, Object body, String msg) {
        return wrapApiResponse(code, body, msg, null);
    }

    protected ApiResponse wrapApiResponse(int code, Object body, String msg, String traceMsg) {
        ApiResponse response = new ApiResponse();
        response.setStatusCode(code);
        response.setMsg(msg);
        if(StringUtils.isEmpty(traceMsg)){
            response.setTraceMsg("traceId: " + TraceContext.getContext().getTraceId());
        }else {
            response.setTraceMsg(traceMsg);
        }
        response.setTimestamp(System.currentTimeMillis()/1000);
        response.setSignType(null);
        response.setBody(body);
        return response;
    }

    protected String encodeJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 获取用户会话信息
     * @return
     */
//    protected SessionUser getSessionUser(){
//        SessionUser currentUser = UserContext.<SessionUser>getUserContext().getCurrentUser();
//        return currentUser;
//    }

}
