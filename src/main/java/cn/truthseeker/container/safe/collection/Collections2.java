package cn.truthseeker.container.safe.collection;

import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public class Collections2 {
    public static <T> boolean anySatisfied(Iterable<T> objects, Predicate<T> predicate){
        for (T object : objects) {
            if(predicate.test(object)){
                return true;
            }
        }
        return false;
    }

    public static <T> boolean anySatisfied(T[] objects, Predicate<T> predicate){
        for (T object : objects) {
            if(predicate.test(object)){
                return true;
            }
        }
        return false;
    }
}
