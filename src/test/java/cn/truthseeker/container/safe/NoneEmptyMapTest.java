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
public class NoneEmptyMapTest {
    @Test
    public void init() {
        NoneEmptyMap m1 = new NoneEmptyMap<>();
        NoneEmptyMap m2 = new NoneEmptyMap<>(Maps.of("a",1));
        NoneEmptyMap m3 = new NoneEmptyMap<>(1);
    }

    @Test
    public void putAll() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        a.putAll(Maps.of("b",2));
        Assert.assertEquals(a.size(), 2);
        TestUtil.withException(()->a.putAll(Maps.of("",2)));
        TestUtil.withException(()->a.putAll(Maps.of("a",null)));
        TestUtil.noException(()->a.putAll(Maps.of("a",2)));
    }

    @Test
    public void put() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        TestUtil.withException(()->a.put("",2));
        TestUtil.withException(()->a.put("a",null));
        TestUtil.noException(()->a.put("a",2));
    }

    @Test
    public void get() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        Assert.assertEquals(a.get("a").intValue(),1);
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneEmptyMap.of("a", 1).size(),1);
        Assert.assertEquals(NoneEmptyMap.of("a", 1,"b", 1).size(),2);
        Assert.assertEquals(NoneEmptyMap.of("a", 1,"b", 1,"c", 1).size(),3);
    }

    @Test
    public void getCollector() {
        NoneEmptyMap<String,Integer> collect = NoneEmptyMap.of("a", 2).entrySet().stream().collect(NoneEmptyMap.collector());
        Assert.assertEquals(collect.size(),1);

        NoneEmptyMap<Integer, Integer> collect1 = NoneEmptyMap.of(1, 2, 3, 4).entrySet().parallelStream().collect(NoneEmptyMap.collector());
        Assert.assertEquals(collect1.size(),2);

        TestUtil.withException(()->Maps.of("a", "b", "b", "").entrySet().stream().collect(NoneEmptyMap.collector()));
        Assert.assertEquals(Maps.of("a", "b", "b", "").entrySet().stream().collect(NoneEmptyMap.collectorIgnoreEmpty()).size(),1);
        Assert.assertEquals(Maps.of("a", "b", "b", "").entrySet().parallelStream().collect(NoneEmptyMap.collectorIgnoreEmpty()).size(),1);
    }

    @Test
    public void mapKey() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).mapKey(a -> a + "1");
        Assert.assertEquals(map.get("a1").intValue(),1);
    }

    @Test
    public void mapValue() {
        NoneEmptyMap<String, String> map = NoneEmptyMap.of("a", 1).mapValue(a -> a + "1");
        Assert.assertEquals(map.get("a"),"11");
    }

    @Test
    public void mapKeyValue() {
        NoneEmptyMap<String, String> map = NoneEmptyMap.of("a", 1).mapKeyValue(a -> a + "1", a->a+"1");
        Assert.assertEquals(map.get("a1"),"11");
    }

    @Test
    public void filterByKey() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByKey(a -> a.equals("a"));
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneEmptyMap.of("a", 1).filterByKey(a -> a.equals("b"));
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void filterByValue() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByValue(a -> a==1);
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneEmptyMap.of("a", 1).filterByValue(a ->a==2);
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void filterByKeyValue() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByKeyValue((a,b)->a.equals("a") && b==1);
        Assert.assertEquals(map.get("a").intValue(),1);

        map = NoneEmptyMap.of("a", 1).filterByKeyValue((a,b)->a.equals("a") && b==2);
        Assert.assertEquals(map.get("a"),null);
    }

    @Test
    public void getSubMap() {
        NoneEmptyMap<String, Integer> all = NoneEmptyMap.of("a", 1, "b", 2, "c", 3);
        NoneEmptyMap<String, Integer> part = NoneEmptyMap.of("a", 1, "b", 2);
        NoneEmptyMap<String, Integer> subMap = all.getSubMap(Arrays.asList("a", "b"));
        Assert.assertEquals(part, subMap);
    }
}