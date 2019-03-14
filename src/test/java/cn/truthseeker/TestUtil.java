package cn.truthseeker;

import java.util.function.Consumer;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class TestUtil {

    public static boolean throwException(Consumer consumer){
        try {
            consumer.accept(null);
            return false;
        }catch (Throwable e){
            return true;
        }
    }
}
