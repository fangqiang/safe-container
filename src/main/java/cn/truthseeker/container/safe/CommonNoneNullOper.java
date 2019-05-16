package cn.truthseeker.container.safe;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneNullOper<E> extends CommonCollectionOper<E> {

    default boolean addIfNotNull(E element) {
        if (element != null) {
            add(element);
            return true;
        } else {
            return false;
        }
    }

    default void addAllIfNotNull(Iterable<? extends E> c) {
        for (E e : c) {
            addIfNotNull(e);
        }
    }

    default void addAllIfNotNull(E[] c) {
        for (E e : c) {
            addIfNotNull(e);
        }
    }
}
