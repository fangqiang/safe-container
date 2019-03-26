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

    public static boolean throwException(TestCode testCode) {
        try {
            testCode.exceptException();
            return false;
        } catch (Throwable e) {
            return true;
        }
    }
}
