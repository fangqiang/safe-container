package cn.truthseeker.container;

import java.util.Collection;
import java.util.Objects;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class Collections2 {
    public static <T> void checkSafe(Collection<T> c){
        Objects.requireNonNull(c);
        for (T e : c) {
            Objects.requireNonNull(e);
        }
    }
}
