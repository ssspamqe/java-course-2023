package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task4Test {

    @Test
    @DisplayName(
        "callingInfo should return CallingInfo instance that contains correct className and methodName which called it")
    void check_callingInfo() {
        CallingInfo callingInfo = Task4.callingInfo(new Throwable());


        String className = callingInfo.className();
        String methodName = callingInfo.methodName();


        assertThat(className).isEqualTo("edu.hw2.Task4Test");
        assertThat(methodName).isEqualTo("check_callingInfo");
    }

}
