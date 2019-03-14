package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeMapsTest {

    @Test
    public void getSubMap() {
        SafeMap<String,Integer> all = new SafeMap<>();
        all.put("a",1);
        all.put("b",2);
        all.put("c",3);

        Map<String, Integer> subMap = SafeMaps.getSubMap(all, Arrays.asList("a", "b"));
        Assert.assertTrue(Maps.containsMap(all, subMap));
    }

    @Test
    public void clearNull() {
        Map<String,String> all = new HashMap<>();
        all.put(null,"a");
        all.put("b",null);
        all.put("c","");
        all.put("d","  \r \t \n ");

        Assert.assertTrue(all.size()==4);
        SafeMap<String, String> safeMap = SafeMaps.clearNull(all);
        Assert.assertTrue(safeMap.size()==2);
    }

    @Test
    public void clearEmpty() {
        Map<String,String> all = new HashMap<>();
        all.put(null,"a");
        all.put("b",null);
        all.put("c","");
        all.put("d","  \r \t \n ");

        Assert.assertTrue(all.size()==4);
        SafeMaps.clearEmpty(all);
        Assert.assertTrue(all.size()==0);
    }

    @Test
    public void transformKeyType() {
        SafeMap<String,String> all = new SafeMap<>();
        all.put("1","1");
        SafeMap<Integer, String> ret = SafeMaps.transformKeyType(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1).equals("1"));
    }

    @Test
    public void transformValueType() {
        SafeMap<String,String> all = new SafeMap<>();
        all.put("1","1");
        SafeMap<String,Integer> ret = SafeMaps.transformValueType(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get("1")==1);
    }

    @Test
    public void transformKeyValueType() {
        SafeMap<String,String> all = new SafeMap<>();
        all.put("1","1");
        SafeMap<Integer,Integer> ret = SafeMaps.transformKeyValueType(all, (a) -> Integer.parseInt(a), (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1)==1);
    }
}