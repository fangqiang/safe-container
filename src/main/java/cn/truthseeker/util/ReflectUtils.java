package cn.truthseeker.util;

import java.lang.reflect.Field;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/29
 */
public class ReflectUtils {

    public static <T> T reflectInnerInstance(Object instance, String point, Class<T> t) throws Exception{
        Object ret = instance;

        String[] split = point.split("\\.");
        for (String field : split) {
            Field classMap = ret.getClass().getDeclaredField(field);
            classMap.setAccessible(true);
            ret = classMap.get(ret);
        }

        return (T) ret;
    }
}
