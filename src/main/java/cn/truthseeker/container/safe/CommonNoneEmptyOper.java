package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneEmptyOper<E> extends CommonCollectionOper<E> {

    default boolean addIgnoreEmpty(E element) {
        if(Emptys.isNotEmpty(element)){
            add(element);
            return true;
        }else{
            return false;
        }
    }

    default void addAllIgnoreEmpty(Iterable<? extends E> c) {
        for (E e : c) {
            addIgnoreEmpty(e);
        }
    }

    default void addAllIgnoreEmpty(E[] c) {
        for (E e : c) {
            addIgnoreEmpty(e);
        }
    }
}
