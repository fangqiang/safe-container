package cn.truthseeker.container.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class Assert {
    public static void isTrue(boolean bool, String message) {
        if (!bool) {
            throw new RuntimeException(message);
        }
    }

    public static <T> void checkSafe(Collection<T> c) {
        Objects.requireNonNull(c);
        for (T e : c) {
            Objects.requireNonNull(e);
        }
    }
}
