package cn.truthseeker.container.util;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class Utils {
    public static boolean isNoneNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNoneEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(Object object) {
        return object == null || (object instanceof String && "".equals(((String) object).trim()));
    }
}
