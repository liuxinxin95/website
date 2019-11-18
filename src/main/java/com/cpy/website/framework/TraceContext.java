
package com.cpy.website.framework;

public class TraceContext {
    private ThreadLocal<String> traceThreadLocal;

    private TraceContext() {
        this.traceThreadLocal = new ThreadLocal();
    }

    public static TraceContext getContext() {
        return TraceContext.TraceContextHolder.INSTANCE;
    }

    public String getTraceId() {
        String user = (String)this.traceThreadLocal.get();
        return user;
    }

    public void setTraceId(String traceId) {
        this.traceThreadLocal.set(traceId);
    }

    private static class TraceContextHolder {
        private static final TraceContext INSTANCE = new TraceContext();

        private TraceContextHolder() {
        }
    }
}
