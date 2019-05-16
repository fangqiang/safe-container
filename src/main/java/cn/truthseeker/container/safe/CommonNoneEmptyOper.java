package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneEmptyOper<E> extends CommonCollectionOper<E> {

    /**
     * 如果element为empty，add成功，返回true
     * 否则，忽略，返回false
     */
    default boolean addIfNotEmpty(E element) {
        if (Emptys.isNotEmpty(element)) {
            add(element);
            return true;
        } else {
            return false;
        }
    }

    /**
     * addAll，忽略集合中的empty元素
     */
    default void addAllOmitEmpty(Iterable<? extends E> c) {
        for (E e : c) {
            addIfNotEmpty(e);
        }
    }

    /**
     * addAll，忽略集合中的empty元素
     */
    default void addAllOmitEmpty(E[] c) {
        for (E e : c) {
            addIfNotEmpty(e);
        }
    }
}
