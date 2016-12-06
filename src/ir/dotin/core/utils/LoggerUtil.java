package ir.dotin.core.utils;

import org.jboss.logging.Logger;

public class LoggerUtil<T extends Exception> {

    protected final static Logger log = Logger.getLogger(LoggerUtil.class.getName());

    protected String createExceptionCause(T e) {
        StackTraceElement stack[] = e.getStackTrace();
        StringBuilder error = new StringBuilder();
        error.append("Exception : ");
        error.append(e.fillInStackTrace());
        error.append("\n");
        int i = 0;
        while (stack.length > i && stack[i].getLineNumber() > 0) {
            String filename = stack[i].getFileName();
            if (filename == null) {
                error.append(" ");
                error.append("filename is not available");
                error.append(", ");
            }
            String className = stack[i].getClassName();
            error.append("ClassName : ");
            error.append(className);
            error.append(", ");
            String methodName = stack[i].getMethodName();
            error.append("MethodName : ");
            error.append(methodName);
            error.append(", ");
            int line = stack[i].getLineNumber();
            error.append("LineNumber : ");
            error.append(line);
            error.append("\n");
            i++;
        }
        return error.toString();
    }

}
