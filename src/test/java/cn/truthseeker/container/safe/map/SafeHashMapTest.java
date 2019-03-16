package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeHashMapTest {

    @Test
    public void init() {
        Map<Object, Object> map = Maps.of(null, null);
        Assert.assertTrue(TestUtil.throwException(() -> new SafeHashMap(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException(() -> new SafeHashMap(map)));

        new SafeHashMap(1);
        new SafeHashMap(1, 0.5f);
    }

    @Test
    public void put() {
        SafeHashMap hashMap = new SafeHashMap();
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.put(null, "")));
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.put("", null)));

        Assert.assertFalse(TestUtil.throwException(() -> hashMap.put("", "")));
        Assert.assertFalse(TestUtil.throwException(() -> hashMap.put("", "")));

        hashMap.clear();
        hashMap.put("a","a");
        Assert.assertTrue(hashMap.size()==1);
        hashMap.putIgnoreNull("b",null);
        Assert.assertTrue(hashMap.size()==1);
        hashMap.putIgnoreEmpty("b","");
        Assert.assertTrue(hashMap.size()==1);
        hashMap.putIgnoreNull("b","");
        Assert.assertTrue(hashMap.size()==2);
        hashMap.putIgnoreEmpty("c","c");
        Assert.assertTrue(hashMap.size()==3);
    }

    @Test
    public void putAll() {
        Map<Object, Object> map = Maps.of(null, null);

        SafeHashMap hashMap = new SafeHashMap();
        Assert.assertTrue(TestUtil.throwException(() -> hashMap.putAll(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException(() -> hashMap.putAll(map)));
    }

    @Test
    public void getNullable() {
        SafeHashMap safeHashMap = new SafeHashMap();
        Assert.assertFalse(safeHashMap.getNullable("").isPresent());
    }

    @Test
    public void newInstance() {
        SafeHashMap safeHashMap = new SafeHashMap();
        safeHashMap.newInstance();
    }

    @Test
    public void get() {
        SafeHashMap safeHashMap = new SafeHashMap();
        Assert.assertTrue(safeHashMap.get("") == null);
    }

    @Test
    public void mapKey() {
        SafeHashMap<String, String> all = Maps.of("1", "1", SafeHashMap::new);

        SafeMap<Integer, String> ret = all.mapKey((a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable(1).get().equals("1"));
    }

    @Test
    public void mapValue() {
        SafeHashMap<String, String> all = Maps.of("1", "1", SafeHashMap::new);

        SafeMap<String, Integer> ret = all.mapValue((a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable("1").get() == 1);
    }

    @Test
    public void mapKeyValue() {
        SafeHashMap<String, String> all = Maps.of("1", "1", SafeHashMap::new);

        SafeMap<Integer, Integer> ret = all.mapKeyValue((a) -> Integer.parseInt(a), (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable(1).get() == 1);
    }

    @Test
    public void filterByKey() {
        SafeHashMap<String, String> all = Maps.of("a", "1", "b", "1", SafeHashMap::new);

        Assert.assertTrue(all.filterByKey(a -> a.equals("a")).size() == 1);
        Assert.assertTrue(all.filterByKey(a -> a.equals("c")).size() == 0);
    }

    @Test
    public void filterByValue() {
        SafeHashMap<String, String> all = Maps.of("a", "1", "b", "2", SafeHashMap::new);

        Assert.assertTrue(all.filterByValue(a -> a.equals("1")).size() == 1);
        Assert.assertTrue(all.filterByValue(a -> a.equals("3")).size() == 0);
    }

    @Test
    public void filterByKeyValue() {
        SafeHashMap<String, String> all = Maps.of("a", "1", "b", "2", SafeHashMap::new);

        Assert.assertTrue(all.filterByKeyValue((a, b) -> a.equals("a") && b.equals("1")).size() == 1);
        Assert.assertTrue(all.filterByKeyValue((a, b) -> a.equals("a") && b.equals("2")).size() == 0);
    }

    @Test
    public void containsMap() {
        SafeHashMap<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        SafeHashMap<String, Integer> part = Maps.of("a", 1, "b", 2, SafeHashMap::new);
        Assert.assertTrue(all.containsMap(part));
        part.put("b", 3);
        Assert.assertFalse(all.containsMap(part));
    }

    @Test
    public void getSubMap() {
        SafeHashMap<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        Map<String, Integer> subMap = all.getSubMap(Arrays.asList("a", "b"));
        Assert.assertTrue(all.containsMap(subMap));
    }

    @Test
    public void containsKeys() {
        SafeHashMap<String, Integer> all = Maps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        Assert.assertTrue(all.containsKeys(Arrays.asList("a", "b")));
        Assert.assertFalse(all.containsKeys(Arrays.asList("a", "d")));
    }

    @Test
    public void firstKeyValue() {
        SafeHashMap<String, Integer> all = Maps.of("a", 1, SafeHashMap::new);
        Assert.assertTrue(all.getTheOnlyKey().equals("a"));
        Assert.assertTrue(all.getTheOnlyValue() == 1);

        all.put("b",1);
        Assert.assertTrue(TestUtil.throwException(()->all.getTheOnlyKey()));
    }

    @Test
    public void removeIf() {
        SafeHashMap<String, Integer> all = Maps.of("a", 1,"b", 2, SafeHashMap::new);
        Assert.assertTrue(all.size()==2);
        all.removeIf((k,v)-> k.equals("a") && v==1);
        Assert.assertTrue(all.size()==1);
        Assert.assertTrue(all.getTheOnlyKey().equals("b"));
    }
}