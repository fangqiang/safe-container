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
        NoneEmptyMap m3 = new NoneEmptyMap<>(1);
    }

    @Test
    public void putAll() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        a.putAll(Maps.of("b", 2));
        Assert.assertEquals(a.size(), 2);
        TestUtil.withException(() -> a.putAll(Maps.of("", 2)));
        TestUtil.withException(() -> a.putAll(Maps.of("a", null)));
        TestUtil.noException(() -> a.putAll(Maps.of("a", 2)));
    }

    @Test
    public void put() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        TestUtil.withException(() -> a.put("", 2));
        TestUtil.withException(() -> a.put("a", null));
        TestUtil.noException(() -> a.put("a", 2));
    }

    @Test
    public void get() {
        NoneEmptyMap<String, Integer> a = NoneEmptyMap.of("a", 1);
        Assert.assertEquals(a.size(), 1);
        Assert.assertEquals(a.get("a").intValue(), 1);
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneEmptyMap.of("a", 1).size(), 1);
        Assert.assertEquals(NoneEmptyMap.of("a", 1, "b", 1).size(), 2);
        Assert.assertEquals(NoneEmptyMap.of("a", 1, "b", 1, "c", 1).size(), 3);
        Assert.assertEquals(NoneEmptyMap.extractFrom(Maps.of("a", "1", "b", "")).size(), 1);
    }

    @Test
    public void mapKey() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).mapByKey(a -> a + "1");
        Assert.assertEquals(map.get("a1").intValue(), 1);
    }

    @Test
    public void mapValue() {
        NoneEmptyMap<String, String> map = NoneEmptyMap.of("a", 1).mapByValue(a -> a + "1");
        Assert.assertEquals(map.get("a"), "11");
    }

    @Test
    public void mapKeyValue() {
        NoneEmptyMap<String, String> map = NoneEmptyMap.of("a", 1).mapByKeyValue(a -> a + "1", a -> a + "1");
        Assert.assertEquals(map.get("a1"), "11");
    }

    @Test
    public void filterByKey() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByKey(a -> a.equals("a"));
        Assert.assertEquals(map.get("a").intValue(), 1);

        map = NoneEmptyMap.of("a", 1).filterByKey(a -> a.equals("b"));
        Assert.assertEquals(map.get("a"), null);
    }

    @Test
    public void filterByValue() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByValue(a -> a == 1);
        Assert.assertEquals(map.get("a").intValue(), 1);

        map = NoneEmptyMap.of("a", 1).filterByValue(a -> a == 2);
        Assert.assertEquals(map.get("a"), null);
    }

    @Test
    public void filterByKeyValue() {
        NoneEmptyMap<String, Integer> map = NoneEmptyMap.of("a", 1).filterByKeyValue((a, b) -> a.equals("a") && b == 1);
        Assert.assertEquals(map.get("a").intValue(), 1);

        map = NoneEmptyMap.of("a", 1).filterByKeyValue((a, b) -> a.equals("a") && b == 2);
        Assert.assertEquals(map.get("a"), null);
    }

    @Test
    public void getSubMap() {
        NoneEmptyMap<String, Integer> all = NoneEmptyMap.of("a", 1, "b", 2, "c", 3);
        NoneEmptyMap<String, Integer> part = NoneEmptyMap.of("a", 1, "b", 2);
        NoneEmptyMap<String, Integer> subMap = all.getSubMap(Arrays.asList("a", "b"));
        Assert.assertEquals(part, subMap);
    }
}