package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Emptys;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public interface SafeSet<E> extends Set<E>, SafeCollection<E>{

    default SafeSet<E> cleanEmpty(){
        removeIf(Emptys::isEmpty);
        return this;
    }

    default SafeSet<E> add2(E e){
        add(e);
        return this;
    }

    default SafeSet<E> traverse(Consumer<E> consumer){
        for (E e : this) {
            consumer.accept(e);
        }
        return this;
    }
}
