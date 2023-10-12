package edu.hw2.Task4;

public class Task4 {

    private Task4() {
    }

    public static CallingInfo callingInfo(Throwable throwable) {
        var stackTrace = throwable.getStackTrace();

        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
