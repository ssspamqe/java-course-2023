package edu.hw2.Task4;

import java.util.Arrays;
import java.util.function.Function;

public class Task4Main {

    static CallingInfo callingInfo(Throwable throwable){
        var stackTrace = throwable.getStackTrace();

        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();
        return new CallingInfo(className, methodName);
    }

    public static void main(String[] params){
        callingInfo(new Throwable());
    }

    void f(){
        System.out.println(callingInfo(new Throwable()));
    }
}
