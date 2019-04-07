package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class CommonMapOperTest {

    @Test
    public void getNullable() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        Assert.assertEquals(a.getNullable("a").get().intValue(),1);
    }

    @Test
    public void getOrCreate() {
        NoneEmptyMap<String, Integer> a = new NoneEmptyMap();
        Assert.assertEquals(a.getOrCreate("a",()->1).intValue(),1);
        Assert.assertEquals(a.get("a").intValue(),1);
    }

    @Test
    public void containsMap() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        NoneEmptyMap<String, Integer> b = NoneEmptyMap.of("a", 1,"b",2);
        Assert.assertTrue(b.containsMap(a));

        a.put("a",2);
        Assert.assertFalse(b.containsMap(a));
    }

    @Test
    public void containsKeys() {
        NoneEmptyMap<String, Integer> m = NoneEmptyMap.of("a", 1,"b",2);
        Assert.assertTrue(m.containsKeys(Arrays.asList("a","b")));
        Assert.assertFalse(m.containsKeys(Arrays.asList("a","c")));
    }

    @Test
    public void getTheOnlyKey() {
        Assert.assertFalse(NoneEmptyMap.of("a", 1,"b",2).getTheOnlyKey().isPresent());
        Assert.assertFalse(new NoneEmptyMap().getTheOnlyKey().isPresent());

        Assert.assertEquals(NoneEmptyMap.of("a", 1).getTheOnlyKey().get(),"a");
    }

    @Test
    public void getTheOnlyValue() {
        Assert.assertFalse(NoneEmptyMap.of("a", 1,"b",2).getTheOnlyValue().isPresent());
        Assert.assertFalse(new NoneEmptyMap().getTheOnlyValue().isPresent());

        Assert.assertEquals(NoneEmptyMap.of("a", 1).getTheOnlyValue().get().intValue(),1);
    }

    @Test
    public void anySatisfied() {
        Assert.assertTrue(NoneEmptyMap.of("a", 1,"b",2).anySatisfied((k,v)-> k.equals("a")));
        Assert.assertFalse(NoneEmptyMap.of("a", 1,"b",2).anySatisfied((k,v)-> k.equals("c")));
    }

    @Test
    public void assertTrue() {
        TestUtil.withException(()->NoneEmptyMap.of("a", 1,"b",2).assertTrue((k,v)->v<2,""));
        TestUtil.noException(()->NoneEmptyMap.of("a", 1,"b",2).assertTrue((k,v)->v<3,""));
    }
}