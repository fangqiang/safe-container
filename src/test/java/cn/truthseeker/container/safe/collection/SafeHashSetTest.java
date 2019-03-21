package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Safes;
import cn.truthseeker.container.safe.collection.SafeHashSet;
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
        Assert.assertTrue(set.size()==1);
        set.addIgnoreNull(null);
        Assert.assertTrue(set.size()==1);
        set.addIgnoreEmpty("");
        Assert.assertTrue(set.size()==1);
        set.addIgnoreNull("");
        Assert.assertTrue(set.size()==2);
        set.addIgnoreEmpty("1");
        Assert.assertTrue(set.size()==3);
    }

    @Test
    public void addAll() {
        SafeHashSet set = new SafeHashSet();
        Assert.assertTrue(TestUtil.throwException(() -> set.addAll(Arrays.asList(null))));
    }

    @Test
    public void anySatisfied(){
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1,2)).anySatisfied((a)->a==1));
        Assert.assertTrue(!Safes.newSafeListIgnoreNull(Arrays.asList(1,2)).anySatisfied((a)->a==3));
    }

    @Test
    public void add2(){
        Assert.assertTrue(Safes.newSafeSet().add2(1).add2(1).size()==1);
    }

    @Test
    public void traverse(){
        Assert.assertTrue(Safes.newSafeSet().add2(1).traverse(a-> System.out.println(a)).size()==1);
    }
}