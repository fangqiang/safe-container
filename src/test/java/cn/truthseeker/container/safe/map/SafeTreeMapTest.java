package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertTrue(TestUtil.throwException((a) -> new SafeTreeMap(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException((a) -> new SafeTreeMap(map)));

        new SafeTreeMap(new TreeMap<>());
        new SafeTreeMap((o1, o2) -> 0);
    }

    @Test
    public void put() {
        SafeTreeMap hashMap = new SafeTreeMap();
        Assert.assertTrue(TestUtil.throwException((a) -> hashMap.put(null, "")));
        Assert.assertTrue(TestUtil.throwException((a) -> hashMap.put("", null)));

        Assert.assertFalse(TestUtil.throwException((a) -> hashMap.put("", "")));
        Assert.assertFalse(TestUtil.throwException((a) -> hashMap.put("", "")));
    }

    @Test
    public void putAll() {
        Map<Object, Object> map = Maps.of(null, null);

        SafeTreeMap hashMap = new SafeTreeMap();
        Assert.assertTrue(TestUtil.throwException((a) -> hashMap.putAll(map)));

        map.clear();
        map.put("", "");
        Assert.assertFalse(TestUtil.throwException((a) -> hashMap.putAll(map)));
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
}