package cn.truthseeker.container.safe.set;

import cn.truthseeker.container.util.Emptys;
import cn.truthseeker.container.util.Utils;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public interface SafeSet<E> extends Set<E> {
    default void addIgnoreNull(E e){
        if(e != null){
            add(e);
        }
    }

    default void addIgnoreEmpty(E e){
        if(Emptys.isNotEmpty(e)){
            add(e);
        }
    }

    default SafeSet<E> cleanEmpty(){
        removeIf(Emptys::isEmpty);
        return this;
    }

    default boolean anySatisfied(Predicate<E> predicate){
        return Utils.anySatisfied(this, predicate);
    }

    default SafeSet<E> add2(E e){
        add(e);
        return this;
    }
}
