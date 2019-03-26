package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Emptys;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface SafeList<E> extends List<E>, SafeCollection<E> {

    default SafeList<E> removeIf2(Predicate<E> predicate) {
        removeIf(predicate);
        return this;
    }

    default SafeList<E> cleanEmpty() {
        removeIf(Emptys::isEmpty);
        return this;
    }

    default SafeList<E> forEach2(Consumer<E> consumer) {
        for (E e : this) {
            consumer.accept(e);
        }
        return this;
    }
}