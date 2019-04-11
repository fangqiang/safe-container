package cn.truthseeker.container.safe;

import java.util.Collection;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneNullOper<E> extends CommonCollectionOper<E> {
    
    default boolean addIgnoreNull(E element) {
        if(element != null){
            add(element);
            return true;
        }else{
            return false;
        }
    }

    default void addAllIgnoreNull(Iterable<? extends E> c) {
        for (E e : c) {
            addIgnoreNull(e);
        }
    }

    default void addAllIgnoreNull(E[] c) {
        for (E e : c) {
            addIgnoreNull(e);
        }
    }
}
