package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Safes;
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
        TestUtil.withException(() -> new SafeArrayList(list));
        list.clear();
        TestUtil.noException(() -> new SafeArrayList(list));

        new SafeArrayList<>(1);
    }

    @Test
    public void add() {
        SafeArrayList list = new SafeArrayList();
        TestUtil.withException(() -> list.add(null));
        TestUtil.withException(() -> list.add(1, null));

        TestUtil.noException(() -> list.add(1));
        TestUtil.noException(() -> list.add(1, 1));
    }

    @Test
    public void addAll() {
        SafeArrayList list = new SafeArrayList();

        List l = new ArrayList();
        l.add(null);
        l.add(null);
        TestUtil.withException(() -> list.addAll(l));
        TestUtil.withException(() -> list.addAll(1, l));

        TestUtil.noException(() -> list.addAll(Arrays.asList(1)));
        TestUtil.noException(() -> list.addAll(1, Arrays.asList(1)));
    }

    @Test
    public void set() {
        SafeArrayList list = new SafeArrayList();
        list.add(1);
        list.set(0, 1);
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void anySatisfied() {
        Assert.assertTrue(Safes.newSafeSetIgnoreNull(Arrays.asList(1, 2)).anySatisfied((a) -> a == 1));
        Assert.assertTrue(!Safes.newSafeSetIgnoreNull(Arrays.asList(1, 2)).anySatisfied((a) -> a == 3));
    }

    @Test
    public void listToMap() {
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1)).toSafeMapIgnoreNull(a -> "").size() == 1);
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1)).toSafeMapIgnoreNull(a -> null).size() == 0);
    }

    @Test
    public void forEach2() {
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1)).forEach2(a -> System.out.println(a)).size() == 1);
    }

    @Test
    public void removeIf2() {
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1)).removeIf2(a -> (int) a == 1).size() == 0);
    }

    @Test
    public void map() {
        Assert.assertTrue(Safes.newSafeListIgnoreNull(Arrays.asList(1)).map(a -> a).size() == 1);
    }

    @Test
    public void assertTrue(){
        TestUtil.noException(()->Safes.newSafeListIgnoreNull(Arrays.asList(1,2)).assertTrue(e-> e<3, ""));
        TestUtil.withException(()->Safes.newSafeListIgnoreNull(Arrays.asList(1,2)).assertTrue(e-> e<2, ""));
    }
}