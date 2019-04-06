package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.collection.SafeList;
import cn.truthseeker.container.safe.collection.SafeSet;
import cn.truthseeker.container.safe.map.CommonMaps;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.NoneEmptyMap;
import cn.truthseeker.container.safe.map.SafeMap;
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
        Assert.assertTrue(Safes.newSafeMap(1, 1) != null);
        Assert.assertTrue(Safes.newSafeMap(1, 1, 1, 1) != null);
        Assert.assertTrue(Safes.newSafeMap(1, 1, 1, 1, 1, 1) != null);
        Assert.assertTrue(Safes.newSafeSortMap() != null);
        Assert.assertTrue(Safes.newSafeSortMap(1, 1) != null);
        Assert.assertTrue(Safes.newSafeSortMap(1, 1, 1, 1) != null);
        Assert.assertTrue(Safes.newSafeSortMap(1, 1, 1, 1, 1, 1) != null);
    }

    @Test
    public void newSafeMapIgnoreNull() {
        Map<String, String> map = CommonMaps.of(null, null, "b", "", "a", "1", HashMap::new);
        Assert.assertTrue(map.size() == 3);
        SafeMap<String, String> safeMap = Safes.newSafeMapIgnoreNull(map);
        Assert.assertTrue(safeMap.size() == 2);
        safeMap = Safes.newSafeSortMapIgnoreNull(map);
        Assert.assertTrue(safeMap.size() == 2);
    }

    @Test
    public void newSafeMapIgnoreEmpty() {
        Map<String, String> map = CommonMaps.of(null, null, "b", "", "a", "1", HashMap::new);
        Assert.assertTrue(map.size() == 3);
        SafeMap<String, String> safeMap = Safes.newSafeMapIgnoreNull(map);
        Assert.assertTrue(safeMap.size() == 2);
        safeMap.removeEmpty();
        Assert.assertTrue(safeMap.size() == 1);
        safeMap = Safes.newSafeSortMapIgnoreNull(map).removeEmpty();
        Assert.assertTrue(safeMap.size() == 1);

        HashMap<String, List<?>> map1 = CommonMaps.of("a", Arrays.asList(), "b", Arrays.asList(1), HashMap::new);
        Assert.assertTrue(map1.size() == 2);
        SafeMap<String, List<?>> map2 = Safes.newSafeMapIgnoreNull(map1);
        Assert.assertTrue(map2.size() == 2);
        map2.removeEmpty();
        Assert.assertTrue(map2.size() == 1);
    }

    @Test
    public void newSafeList() {
        Assert.assertTrue(Safes.newSafeList() != null);
        Assert.assertTrue(Safes.newSafeLinkedList() != null);
    }

    @Test
    public void newSafeListIgnoreNull() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeList<String> l = Safes.newSafeListIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);
        l = Safes.newSafeLinkedListIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);
    }

    @Test
    public void newSafeListIgnoreEmpty() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeList<String> l = Safes.newSafeListIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);
        l.cleanEmpty();
        Assert.assertTrue(l.size() == 1);
        l = Safes.newSafeLinkedListIgnoreNull(list).cleanEmpty();
        Assert.assertTrue(l.size() == 1);

        Assert.assertTrue(Safes.newSafeListIgnoreNull("1,2".split(",")).size() == 2);
        Assert.assertTrue(Safes.newSafeLinkedListIgnoreNull("1,2".split(",")).size() == 2);
    }

    @Test
    public void newSafeSet() {
        Assert.assertTrue(Safes.newSafeSet() != null);
        Assert.assertTrue(Safes.newSafeSortSet() != null);
    }

    @Test
    public void newSafeSetIgnoreNull() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeSet<String> l = Safes.newSafeSetIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);
        l = Safes.newSafeSortSetIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);

        Assert.assertTrue(Safes.newSafeSetIgnoreNull("1,2".split(",")).size() == 2);
        Assert.assertTrue(Safes.newSafeSortSetIgnoreNull("1,2".split(",")).size() == 2);
    }

    @Test
    public void newSafeSetIgnoreEmpty() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size() == 3);
        SafeSet<String> l = Safes.newSafeSetIgnoreNull(list);
        Assert.assertTrue(l.size() == 2);
        l.cleanEmpty();
        Assert.assertTrue(l.size() == 1);
        l = Safes.newSafeSortSetIgnoreNull(list).cleanEmpty();
        Assert.assertTrue(l.size() == 1);
    }
    @Test
    public void newNoneEmptyMap() {
        NoneEmptyMap<String, String> neMap = Safes.newNoneEmptyMap(Maps.of("a", "a"));
        Assert.assertTrue(neMap.size()==1);

        neMap = Safes.newNoneEmptyMap(Maps.of("", ""));
        Assert.assertTrue(neMap.size()==0);
        neMap = Safes.newNoneEmptyMap(Maps.of("a", ""));
        Assert.assertTrue(neMap.size()==0);
    }
}