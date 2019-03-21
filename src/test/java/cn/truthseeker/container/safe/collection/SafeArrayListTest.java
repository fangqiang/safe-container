package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Safes;
import cn.truthseeker.container.safe.collection.SafeArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeArrayListTest {

    @Test
    public void init() {
        List list = new ArrayList();
        list.add(null);
        list.add(null);
        Assert.assertTrue(TestUtil.throwException(() -> new SafeArrayList(list)));
        list.clear();
        Assert.assertFalse(TestUtil.throwException(() -> new SafeArrayList(list)));

        new SafeArrayList<>(1);
    }

    @Test
    public void add() {
        SafeArrayList list = new SafeArrayList();
        Assert.assertTrue(TestUtil.throwException(() -> list.add(null)));
        Assert.assertTrue(TestUtil.throwException(() -> list.add(1, null)));

        Assert.assertFalse(TestUtil.throwException(() -> list.add(1)));
        Assert.assertFalse(TestUtil.throwException(() -> list.add(1, 1)));
    }

    @Test
    public void addAll() {
        SafeArrayList list = new SafeArrayList();
        Assert.assertTrue(TestUtil.throwException(() -> list.addAll(null)));
        Assert.assertTrue(TestUtil.throwException(() -> list.addAll(1, null)));

        Assert.assertFalse(TestUtil.throwException(() -> list.addAll(Arrays.asList(1))));
        Assert.assertFalse(TestUtil.throwException(() -> list.addAll(1, Arrays.asList(1))));
    }

    @Test
    public void set() {
        SafeArrayList list = new SafeArrayList();
        list.add(1);
        list.set(0, 1);

        Assert.assertTrue(list.size()==1);
        list.addIgnoreNull(null);
        Assert.assertTrue(list.size()==1);
        list.addIgnoreEmpty("");
        Assert.assertTrue(list.size()==1);
        list.addIgnoreNull("");
        Assert.assertTrue(list.size()==2);
        list.addIgnoreEmpty("1");
        Assert.assertTrue(list.size()==3);
    }

    @Test
    public void anySatisfied(){
        Assert.assertTrue(Safes.newSafeSetIgnoreNull(Arrays.asList(1,2)).anySatisfied((a)->a==1));
        Assert.assertTrue(!Safes.newSafeSetIgnoreNull(Arrays.asList(1,2)).anySatisfied((a)->a==3));
    }

    @Test
    public void add2(){
        Assert.assertTrue(Safes.newSafeList().add2(1).size()==1);
    }

    @Test
    public void listToMap(){
        Assert.assertTrue(Safes.newSafeList().add2(1).toMap(a->"a").size()==1);
        Assert.assertTrue(Safes.newSafeList().add2(1).toMap(a->null).size()==1);
        Assert.assertTrue(Safes.newSafeList().add2(1).toSafeMapIgnoreNull(a->"").size()==1);
        Assert.assertTrue(Safes.newSafeList().add2(1).toSafeMapIgnoreNull(a->null).size()==0);
    }
}