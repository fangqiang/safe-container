package cn.truthseeker.container;

import cn.truthseeker.TestUtil;
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
public class MapsTest {

    @Test
    public void checkSafe() {
        Map map = new HashMap<>();
        map.put(null,null);
        Assert.assertTrue(TestUtil.throwException((a)->Maps.checkSafe(map)));
        map.clear();
        Assert.assertFalse(TestUtil.throwException((a)->Maps.checkSafe(map)));
        map.put("1","");
        Assert.assertFalse(TestUtil.throwException((a)->Maps.checkSafe(map)));

    }

    @Test
    public void containsMap() {
        Map<String,Integer> all = new HashMap<>();
        Map<String,Integer> part = new HashMap<>();
        all.put("a",1);
        all.put("b",2);
        all.put("c",3);
        part.put("a",1);
        part.put("b",2);
        Assert.assertTrue(Maps.containsMap(all, part));
        part.put("b",3);
        Assert.assertFalse(Maps.containsMap(all, part));

        all.put("b",null);
        part.put("b",null);
        Assert.assertTrue(Maps.containsMap(all, part));
    }

    @Test
    public void getSubMap() {
        Map<String,Integer> all = new HashMap<>();
        all.put("a",1);
        all.put("b",2);
        all.put("c",3);

        Map<String, Integer> subMap = Maps.getSubMap(all, Arrays.asList("a", "b"));
        Assert.assertTrue(Maps.containsMap(all, subMap));
    }

    @Test
    public void clearEmpty() {
        Map<String,String> all = new HashMap<>();
        all.put(null,"a");
        all.put("b",null);
        all.put("c","");

        Assert.assertTrue(all.size()==3);
        Maps.clearNull(all);
        Assert.assertTrue(all.size()==1);

    }

    @Test
    public void clearNull() {
        Map<String,String> all = new HashMap<>();
        all.put(null,"a");
        all.put("b",null);
        all.put("c","");
        all.put("d","  \r \t \n ");

        Assert.assertTrue(all.size()==4);
        Maps.clearEmpty(all);
        Assert.assertTrue(all.size()==0);
    }

    @Test
    public void transformKeyType() {
        Map<String,String> all = new HashMap<>();
        all.put("1","1");
        Map<Integer, String> ret = Maps.transformKeyType(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1).equals("1"));
    }

    @Test
    public void transformValueType() {
        Map<String,String> all = new HashMap<>();
        all.put("1","1");
        Map<String,Integer> ret = Maps.transformValueType(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get("1")==1);
    }

    @Test
    public void transformKeyValueType() {
        Map<String,String> all = new HashMap<>();
        all.put("1","1");
        Map<Integer,Integer> ret = Maps.transformKeyValueType(all, (a) -> Integer.parseInt(a), (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1)==1);
    }
}