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
public class NoneEmptyList<E> extends ArrayList<E> implements CommonCollectionOper<E> {
    public NoneEmptyList() {
        super();
    }

    public NoneEmptyList(Collection<? extends E> c) {
        super();
        addAll(c);
    }

    public NoneEmptyList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {
        Emptys.assertNotEmpty(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        Emptys.assertNotEmpty(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Emptys.assertNotEmpty(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneEmpty(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Emptys.assertNoneEmpty(c);
        return super.addAll(index, c);
    }


    // 构造工具
    public static <E> NoneEmptyList<E> of(E... e) {
        return Collections2.of(NoneEmptyList::new, e);
    }

    public static <E> Collector<E, ?, NoneEmptyList<E>> getCollector() {
        return Collector.of(
                NoneEmptyList::new,
                NoneEmptyList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    // 简化操作
    public <R> NoneEmptyList<R> map(Function<E, R> function) {
        return stream().map(function).collect(getCollector());
    }
}