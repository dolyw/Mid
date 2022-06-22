package com.pcic.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * MessageException自定义异常
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/10/12 16:46
 */
public class MessageException extends RuntimeException {

    private static final long serialVersionUID = -512526081281908583L;

    public MessageException(final String message) {
        super(message);
    }

    @Override
    public void printStackTrace(PrintStream ps) {
        if (getCause() == null) {
            super.printStackTrace(ps);
        } else {
            ps.println(this);
            getCause().printStackTrace(ps);
        }
    }

    @Override
    public void printStackTrace(PrintWriter pw) {
        if (getCause() == null) {
            super.printStackTrace(pw);
        } else {
            pw.println(this);
            getCause().printStackTrace(pw);
        }
    }
}