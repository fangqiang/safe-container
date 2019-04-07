package cn.truthseeker.container.safe;

import cn.truthseeker.container.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneNullList<E> extends ArrayList<E> implements CommonCollectionOper<E> {
    public NoneNullList() {
        super();
    }

    public NoneNullList(Collection<? extends E> c) {
        super();
        addAll(c);
    }

    public NoneNullList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {
        Emptys.assertNotNull(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        Emptys.assertNotNull(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Emptys.assertNotNull(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(index, c);
    }


    // 构造工具
    public static <E> NoneNullList<E> of(E... e) {
        return Collections2.of(NoneNullList::new, e);
    }

    public static <E> Collector<E, ?, NoneNullList<E>> getCollector() { // TODO 测试Characteristics
        return Collector.of(NoneNullList::new, NoneNullList::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collector.Characteristics.IDENTITY_FINISH);
    }

    // 简化操作
    public <R> NoneNullList<R> map(Function<E, R> function) {
        return stream().map(function).collect(getCollector());
    }
}