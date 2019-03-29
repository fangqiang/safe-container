package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.safe.Safes;
import cn.truthseeker.container.util.Emptys;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public interface SafeSet<E> extends Set<E>, SafeCollection<E> {

    default SafeSet<E> removeIf2(Predicate<E> predicate) {
        removeIf(predicate);
        return this;
    }

    default SafeSet<E> cleanEmpty() {
        removeIf(Emptys::isEmpty);
        return this;
    }

    default SafeSet<E> forEach2(Consumer<E> consumer) {
        for (E e : this) {
            consumer.accept(e);
        }
        return this;
    }

    default <R> SafeSet<R> map(Function<E,R> function){
        SafeSet<R> list = Safes.newSafeSet();
        for (E e : this) {
            list.add(function.apply(e));
        }
        return list;
    }
}
