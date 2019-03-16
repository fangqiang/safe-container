package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeTreeMapTest {

    @Test
    public void init() {
        Map<Object, Object> map = Maps.of(null, null);

        Assert.assertTrue(TestUtil.throwException(() -> new SafeTreeMap(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException(() -> new SafeTreeMap(map)));

        new SafeTreeMap(new TreeMap<>());
        new SafeTreeMap((o1, o2) -> 0);
    }

    @Test
    public void put() {
        SafeTreeMap hashMap = new SafeTreeMap();
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.put(null, "")));
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.put("", null)));

        Assert.assertFalse(TestUtil.throwException(() -> hashMap.put("", "")));
        Assert.assertFalse(TestUtil.throwException(() -> hashMap.put("", "")));
    }

    @Test
    public void putAll() {
        Map<Object, Object> map = Maps.of(null, null);

        SafeTreeMap hashMap = new SafeTreeMap();
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.putAll(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException(() -> hashMap.putAll(map)));
    }

    @Test
    public void getNullable() {
        SafeTreeMap safeHashMap = new SafeTreeMap();
        Assert.assertFalse(safeHashMap.getNullable("").isPresent());
    }

    @Test
    public void newInstance() {
        SafeTreeMap safeHashMap = new SafeTreeMap();
        safeHashMap.newInstance();
    }

    @Test
    public void get() {
        SafeTreeMap safeHashMap = new SafeTreeMap();
        Assert.assertTrue(safeHashMap.get("") == null);
    }

    @Test
    public void containsKeys() {
        SafeTreeMap<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3, SafeTreeMap::new);
        Assert.assertTrue(all.containsKeys(Arrays.asList("a", "b")));
        Assert.assertFalse(all.containsKeys(Arrays.asList("a", "d")));
    }

    @Test
    public void firstKeyValue() {
        SafeTreeMap<String, Integer> all = Maps.of("a", 1, SafeTreeMap::new);
        Assert.assertTrue(all.getTheOnlyKey().equals("a"));
        Assert.assertTrue(all.getTheOnlyValue() == 1);

        all.put("b",1);
        Assert.assertTrue(TestUtil.throwException(()->all.getTheOnlyKey()));
    }
}