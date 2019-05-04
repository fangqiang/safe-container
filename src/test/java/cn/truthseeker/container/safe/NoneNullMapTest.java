package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class NoneNullMapTest {
    @Test
    public void init() {
        NoneNullMap m1 = new NoneNullMap<>();
        NoneNullMap m3 = new NoneNullMap<>(1);
    }

    @Test
    public void putAll() {
        NoneNullMap<String, Integer> a = NoneNullMap.of("a", 1);
        a.putAll(Maps.of("b",2));
        Assert.assertEquals(a.size(), 2);
        TestUtil.noException(()->a.putAll(Maps.of("",2)));
        TestUtil.withException(()->a.putAll(Maps.of("a",null)));
        TestUtil.noException(()->a.putAll(Maps.of("a",2)));
    }

    @Test
    public void put() {
        NoneNullMap<String, Integer> a = NoneNullMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        TestUtil.noException(()->a.put("",2));
        TestUtil.withException(()->a.put("a",null));
        TestUtil.noException(()->a.put("a",2));
    }

    @Test
    public void get() {
        NoneNullMap<String, Integer> a = NoneNullMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        Assert.assertEquals(a.get("a").intValue(),1);
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneNullMap.of("a", 1).size(),1);
        Assert.assertEquals(NoneNullMap.of("a", 1,"b", 1).size(),2);
        Assert.assertEquals(NoneNullMap.of("a", 1,"b", 1,"c", 1).size(),3);
        Assert.assertEquals(NoneNullMap.ofIgnoreNull(Maps.of("a","1","b",null)).size(),1);
    }

    @Test
    public void mapKey() {
        NoneNullMap<String, Integer> map = NoneNullMap.of("a", 1).mapKey(a -> a + "1");
        Assert.assertEquals(map.get("a1").intValue(),1);
    }

    @Test
    public void mapValue() {
        NoneNullMap<String, String> map = NoneNullMap.of("a", 1).mapValue(a -> a + "1");
        Assert.assertEquals(map.get("a"),"11");
    }

    @Test
    public void mapKeyValue() {
        NoneNullMap<String, String> map = NoneNullMap.of("a", 1).mapKeyValue(a -> a + "1", a->a+"1");
        Assert.assertEquals(map.get("a1"),"11");
    }

    @Test
    public void filterByKey() {
        NoneNullMap<String, Integer> map = NoneNullMap.of("a", 1).filterByKey(a -> a.equals("a"));
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneNullMap.of("a", 1).filterByKey(a -> a.equals("b"));
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void filterByValue() {
        NoneNullMap<String, Integer> map = NoneNullMap.of("a", 1).filterByValue(a -> a==1);
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneNullMap.of("a", 1).filterByValue(a ->a==2);
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void filterByKeyValue() {
        NoneNullMap<String, Integer> map = NoneNullMap.of("a", 1).filterByKeyValue((a,b)->a.equals("a") && b==1);
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneNullMap.of("a", 1).filterByKeyValue((a,b)->a.equals("a") && b==2);
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void getSubMap() {
        NoneNullMap<String, Integer> all = NoneNullMap.of("a", 1, "b", 2, "c", 3);
        NoneNullMap<String, Integer> part = NoneNullMap.of("a", 1, "b", 2);
        NoneNullMap<String, Integer> subMap = all.getSubMap(Arrays.asList("a", "b"));
        Assert.assertEquals(part, subMap);
    }
}