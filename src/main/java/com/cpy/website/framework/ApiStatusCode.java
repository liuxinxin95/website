//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cpy.website.framework;

public enum ApiStatusCode {
    SYSTEM_ERROR(5000),
    SYSTEM_BUSY(5003),
    SERVICE_INVOKE_EXCEPTION(6100),
    SERVICE_INTERNAL_EXCEPTION(6200),
    SERVICE_BIZ_EXCEPTION(6000),
    PARAM_ERROR(4000),
    MISSING_PARAM(4010),
    INVALID_PARAM(4020),
    UNRECOGNIZED_PARAM(4030),
    UNAUTHORIZED(4001),
    AUTH_INVALID(4002),
    INVALID_SIGN(4003),
    NOT_FOUND(4004),
    OK(2000);

    private final int code;

    private ApiStatusCode(int code) {
        this.code = code;
    }

    public static ApiStatusCode valueOf(int code) {
        ApiStatusCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApiStatusCode apiStatusCode = var1[var3];
            if (apiStatusCode.code == code) {
                return apiStatusCode;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    public int value() {
        return this.code;
    }
}
