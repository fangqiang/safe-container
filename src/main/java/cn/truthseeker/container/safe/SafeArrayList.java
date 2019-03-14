package cn.truthseeker.container.safe;

import cn.truthseeker.container.Collections2;

import java.util.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeArrayList<E> extends ArrayList<E> implements SafeContainer{
    public SafeArrayList() {
        super();
    }

    public SafeArrayList(Collection<? extends E> c) {
        super(c);
        Collections2.checkSafe(c);
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
    public boolean addAll(Collection<? extends E> c) {
        Collections2.checkSafe(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Collections2.checkSafe(c);
        return super.addAll(index, c);
    }
}