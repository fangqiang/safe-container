package cn.truthseeker;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class TestUtil {


    public interface TestCode {
        void exceptException();
    }

    public static void noException(TestCode testCode) {
        testCode.exceptException();
    }

    public static void withException(TestCode testCode) {
        try {
            testCode.exceptException();
        } catch (Throwable e) {
            return;
        }
        throw new RuntimeException("");
    }
}
