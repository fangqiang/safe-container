package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Safes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class SafeHashSetTest {
    @Test
    public void init() {
        new SafeHashSet();
        new SafeHashSet(Arrays.asList(1));
        new SafeHashSet(1, 0.5f);
        new SafeHashSet(1);
    }

    @Test
    public void add() {
        SafeHashSet set = new SafeHashSet();
        Assert.assertTrue(TestUtil.throwException(() -> set.add(null)));

        set.clear();
        set.add("a");
    }

    @Test
    public void addAll() {
        SafeHashSet set = new SafeHashSet();
        Assert.assertTrue(TestUtil.throwException(() -> set.addAll(Arrays.asList(null))));
    }

    @Test
    public void anySatisfied() {
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1, 2)).anySatisfied((a) -> a == 1));
        Assert.assertTrue(!Safes.newSafeListIgnoreNull(Arrays.asList(1, 2)).anySatisfied((a) -> a == 3));
    }

    @Test
    public void add2() {
        Assert.assertTrue(Safes.newSafeSetIgnoreNull(Arrays.asList(1)).size() == 1);
    }

    @Test
    public void forEach2() {
        Assert.assertTrue(Safes.newSafeSetIgnoreNull(Arrays.asList(1)).forEach2(a -> System.out.println(a)).size() == 1);
    }

    @Test
    public void removeIf2() {
        Assert.assertTrue(Safes.newSafeSetIgnoreNull(Arrays.asList(1)).removeIf2(a -> (int) a == 1).size() == 0);
    }
}