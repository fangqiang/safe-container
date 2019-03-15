package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.list.SafeList;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.safe.set.SafeSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class SafesTest {

    @Test
    public void newSafeMap() {
        Assert.assertTrue(Safes.newSafeMap() != null);
        Assert.assertTrue(Safes.newSafeSortMap() != null);
    }

    @Test
    public void newSafeMapOmitNull() {
        Map<String, String> map = Maps.of(null, null, "b", "", "a", "1", HashMap::new);
        Assert.assertTrue(map.size() == 3);
        SafeMap<String, String> safeMap = Safes.newSafeMapOmitNull(map);
        Assert.assertTrue(safeMap.size() == 2);
        safeMap = Safes.newSafeSortMapOmitNull(map);
        Assert.assertTrue(safeMap.size() == 2);
    }

    @Test
    public void newSafeMapOmitEmpty() {
        Map<String, String> map = Maps.of(null, null, "b", "", "a", "1", HashMap::new);
        Assert.assertTrue(map.size() == 3);
        SafeMap<String, String> safeMap = Safes.newSafeMapOmitEmpty(map);
        Assert.assertTrue(safeMap.size() == 1);
        safeMap = Safes.newSafeSortMapOmitEmpty(map);
        Assert.assertTrue(safeMap.size() == 1);
    }

    @Test
    public void newSafeList() {
        Assert.assertTrue(Safes.newSafeList() != null);
        Assert.assertTrue(Safes.newSafeLinkedList() != null);
    }

    @Test
    public void newSafeListOmitNull() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeList<String> l = Safes.newSafeListOmitNull(list);
        Assert.assertTrue(l.size() == 2);
        l = Safes.newSafeLinkedListOmitNull(list);
        Assert.assertTrue(l.size() == 2);
    }

    @Test
    public void newSafeListOmitEmpty() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeList<String> l = Safes.newSafeListOmitEmpty(list);
        Assert.assertTrue(l.size() == 1);
        l = Safes.newSafeLinkedListOmitEmpty(list);
        Assert.assertTrue(l.size() == 1);
    }

    @Test
    public void newSafeSet() {
        Assert.assertTrue(Safes.newSafeSet() != null);
        Assert.assertTrue(Safes.newSafeSortSet() != null);
    }

    @Test
    public void newSafeSetOmitNull() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeSet<String> l = Safes.newSafeSetOmitNull(list);
        Assert.assertTrue(l.size() == 2);
        l = Safes.newSafeSortSetOmitNull(list);
        Assert.assertTrue(l.size() == 2);
    }

    @Test
    public void newSafeSetOmitEmpty() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeSet<String> l = Safes.newSafeSetOmitEmpty(list);
        Assert.assertTrue(l.size() == 1);
        l = Safes.newSafeSortSetOmitEmpty(list);
        Assert.assertTrue(l.size() == 1);
    }
}