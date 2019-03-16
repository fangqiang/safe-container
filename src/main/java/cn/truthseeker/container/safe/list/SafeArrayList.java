package cn.truthseeker.container.safe.list;

import cn.truthseeker.container.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeArrayList<E> extends ArrayList<E> implements SafeList<E> {
    public SafeArrayList() {
        super();
    }

    public SafeArrayList(Collection<? extends E> c) {
        super(c);
        if (!(c instanceof SafeList)) {
            Assert.checkSafe(c);
        }
    }

    public SafeArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        Objects.requireNonNull(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Objects.requireNonNull(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Assert.checkSafe(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Assert.checkSafe(c);
        return super.addAll(index, c);
    }
}