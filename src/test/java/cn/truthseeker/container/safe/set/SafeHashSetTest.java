package cn.truthseeker.container.safe.set;

import cn.truthseeker.TestUtil;
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
    }

    @Test
    public void addAll() {
        SafeHashSet set = new SafeHashSet();
        Assert.assertTrue(TestUtil.throwException(() -> set.addAll(Arrays.asList(null))));
    }
}