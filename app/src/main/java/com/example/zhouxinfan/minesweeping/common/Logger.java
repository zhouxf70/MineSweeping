package com.example.zhouxinfan.minesweeping.common;

import android.content.Context;
import android.util.Log;

public class Logger {

    private static String TAG = "Logger";

    private static boolean isDebug = true;

    private final static int D = 1;
    private final static int I = 2;
    private final static int W = 3;
    private final static int E = 4;

    public void init(Context context, String tag) {
        isDebug = Util.isDebug(context);
        if (Util.notEmpty(tag)) {
            TAG = tag;
        }
    }

    public static void d(String msg) {
        print(D, "", msg);
    }

    public static void i(String msg) {
        print(I, "", msg);
    }

    public static void w(String msg) {
        print(W, "", msg);
    }

    public static void e(String msg) {
        print(E, "", msg);
    }

    public static void d(String tag, String msg) {
        print(D, tag, msg);
    }

    public static void i(String tag, String msg) {
        print(I, tag, msg);
    }

    public static void w(String tag, String msg) {
        print(W, tag, msg);
    }

    public static void e(String tag, String msg) {
        print(E, tag, msg);
    }

    private static void print(int type, String tag, String msg) {
        if (!isDebug)
            return;

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 4;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        if (!methodName.isEmpty() && methodName.charAt(0) >= 'a' && methodName.charAt(0) <= 'z')
            methodName.toCharArray()[0] -= 32;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");

        if (msg != null) {
            stringBuilder.append(msg);
        }

        String allTag = TAG;
        if (Util.notEmpty(tag)) {
            allTag = TAG + "-" + tag;
        }
        String logStr = stringBuilder.toString();
        switch (type) {
            case D:
                Log.d(allTag, logStr);
                break;
            case I:
                Log.i(allTag, logStr);
                break;
            case W:
                Log.w(allTag, logStr);
                break;
            case E:
                Log.e(allTag, logStr);
                break;
        }
    }
}
