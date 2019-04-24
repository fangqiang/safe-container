package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class MapsTest {

    @Test
    public void containsMap() {
        Map<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> part = Maps.of("a", 1, "b", 2);

        Assert.assertTrue(Maps.containsMap(all, part));
        part.put("b", 3);
        Assert.assertFalse(Maps.containsMap(all, part));

        all.put("b", null);
        part.put("b", null);
        Assert.assertTrue(Maps.containsMap(all, part));
    }

    @Test
    public void getSubMap() {
        Map<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> subMap = Maps.getSubMap(all, Arrays.asList("a", "b"));
        Assert.assertTrue(Maps.containsMap(all, subMap));
    }

    @Test
    public void mapKey() {
        Map<String, String> all = Maps.of("1", "1");
        Map<Integer, String> ret = Maps.mapKey(all, Integer::parseInt);
        Assert.assertTrue(ret.get(1).equals("1"));
    }

    @Test
    public void mapValue() {
        Map<String, String> all = Maps.of("1", "1");
        Map<String, Integer> ret = Maps.mapValue(all, Integer::parseInt);
        Assert.assertTrue(ret.get("1") == 1);
    }

    @Test
    public void mapKeyValue() {
        Map<String, String> all = Maps.of("1", "1");
        Map<Integer, Integer> ret = Maps.mapKeyValue(all, Integer::parseInt, Integer::parseInt);
        Assert.assertTrue(ret.get(1) == 1);
    }

    @Test
    public void filterByKey() {
        Map<String, String> all = Maps.of("a", "1", "b", "1");
        Assert.assertTrue(Maps.filterByKey(all, a -> a.equals("a")).size() == 1);
        Assert.assertTrue(Maps.filterByKey(all, a -> a.equals("c")).size() == 0);
    }

    @Test
    public void filterByValue() {
        Map<String, String> all = Maps.of("a", "1", "b", "2");
        Assert.assertTrue(Maps.filterByValue(all, a -> a.equals("1")).size() == 1);
        Assert.assertTrue(Maps.filterByValue(all, a -> a.equals("3")).size() == 0);
    }

    @Test
    public void filterByKeyValue() {
        Map<String, String> all = Maps.of("a", "1", "b", "2");
        Assert.assertTrue(Maps.filterByKeyValue(all, (a, b) -> a.equals("a") && b.equals("1")).size() == 1);
        Assert.assertTrue(Maps.filterByKeyValue(all, (a, b) -> a.equals("a") && b.equals("2")).size() == 0);
    }

    @Test
    public void of() {
        Assert.assertTrue(Maps.of(1, 1).size() == 1);
        Assert.assertTrue(Maps.of(1, "", 2, "").size() == 2);
        Assert.assertTrue(Maps.of(1, "", 2, "", 3, "").size() == 3);
    }

    @Test
    public void clearEmpty() {
        Map<Integer, String> map = Maps.of(1, "1", 2, null, 3, "");
        Assert.assertTrue(Maps.clearEmpty(map).size() == 1);
        Assert.assertTrue(map.containsKey(1));
    }

    @Test
    public void clearNull() {
        Map<Integer, String> map = Maps.of(1, "1", 2, null, 3, "");
        Assert.assertTrue(Maps.clearNull(map).size() == 2);
        Assert.assertTrue(map.containsKey(1));
        Assert.assertTrue(map.containsKey(3));
    }

    @Test
    public void listToMap() {
        Map<Integer, String> map = Maps.listToMap(Arrays.asList(1, 2), a -> a + "a");
        Assert.assertTrue(map.size() == 2);
        Assert.assertEquals(map.get(1),"1a");
        Assert.assertEquals(map.get(2),"2a");
    }

    @Test
    public void zip() {
        Map<String, Integer> zip = Maps.zip(Arrays.asList("a", "b"), Arrays.asList(1, 2));
        Assert.assertTrue(zip.size() == 2);
        Assert.assertTrue(zip.get("a") == 1);
        Assert.assertTrue(zip.get("b") == 2);
    }

    @Test
    public void getOrCreate() {
        Map<String, Integer> map = Maps.of("a", 1);
        Assert.assertTrue(Maps.getOrCreate(map, "b", ()->11) ==11);
        Assert.assertTrue(map.get("b") == 11);
    }

    @Test
    public void assertTrue() {
        Map<String, Integer> map = Maps.of("a", 1);
        TestUtil.noException(()->Maps.assertTrue(map, (k,v)->k.equals("a"), "aa"));
        TestUtil.withException(()->Maps.assertTrue(map, (k,v)->k.equals("b"), "aa"));
    }
}