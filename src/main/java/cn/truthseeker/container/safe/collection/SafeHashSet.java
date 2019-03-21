package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeHashSet<E> extends HashSet<E> implements SafeSet<E> {
    public SafeHashSet() {
        super();
    }

    public SafeHashSet(Collection<? extends E> c) {
        super(c);
        if (!(c instanceof SafeSet)) {
            Assert.checkSafe(c);
        }
    }

    public SafeHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }


    public SafeHashSet(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Assert.checkSafe(c);
        return super.addAll(c);
    }

}