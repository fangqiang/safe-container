package cn.truthseeker.container.safe;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneNullOper<E> extends CommonCollectionOper<E> {

    /**
     * 如果element为null，add成功，返回true
     * 否则，忽略，返回false
     */
    default boolean addIfNotNull(E element) {
        if (element != null) {
            add(element);
            return true;
        } else {
            return false;
        }
    }

    /**
     * addAll，忽略集合中的empty元素
     */
    default void addAllOmitNull(Iterable<? extends E> c) {
        for (E e : c) {
            addIfNotNull(e);
        }
    }

    /**
     * addAll，忽略集合中的empty元素
     */
    default void addAllOmitNull(E[] c) {
        for (E e : c) {
            addIfNotNull(e);
        }
    }
}
