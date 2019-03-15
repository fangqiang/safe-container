package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.list.SafeList;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class SafesTest {

    @Test
    public void newSafeMap() {
        Assert.assertTrue(Safes.newSafeMap()!=null);
    }

    @Test
    public void newSafeMapOmitNull() {
        Map<String,String> map = Maps.of(null,null,"b","","a","1", HashMap::new);
        Assert.assertTrue(map.size()==3);
        SafeMap<String, String> safeMap = Safes.newSafeMapOmitNull(map);
        Assert.assertTrue(safeMap.size()==2);
    }

    @Test
    public void newSafeMapOmitEmpty() {
        Map<String,String> map = Maps.of(null,null,"b","","a","1", HashMap::new);
        Assert.assertTrue(map.size()==3);
        SafeMap<String, String> safeMap = Safes.newSafeMapOmitEmpty(map);
        Assert.assertTrue(safeMap.size()==1);
    }

    @Test
    public void newSafeList() {
        Assert.assertTrue(Safes.newSafeList()!=null);
    }

    @Test
    public void newSafeListOmitNull() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size()==3);
        SafeList<String> l = Safes.newSafeListOmitNull(list);
        Assert.assertTrue(l.size()==2);
    }

    @Test
    public void newSafeListOmitEmpty() {
        List<String> list = Arrays.asList(null, "", "a");
        Assert.assertTrue(list.size()==3);
        SafeList<String> l = Safes.newSafeListOmitEmpty(list);
        Assert.assertTrue(l.size()==1);
    }
}