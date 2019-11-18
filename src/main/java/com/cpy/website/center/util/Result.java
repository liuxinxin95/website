package com.cpy.website.center.util;




import java.util.HashMap;
import java.util.Map;

/**
 * 数据返回
 *
 * @author liuxinxin
 * @email
 * @date 2018/6/27 11:20
 * @param
 * @return
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final Integer ERROR_CODE=0001;


    public Result() {
        put("code", 0);
    }

    public static Result error() {
        return error(ERROR_CODE, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
