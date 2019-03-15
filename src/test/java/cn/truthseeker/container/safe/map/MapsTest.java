package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.map.Maps;
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
    public void checkSafe() {
        Map map = Maps.of(null,null,HashMap::new);
        Assert.assertTrue(TestUtil.throwException((a)-> Maps.checkSafe(map)));
        map.clear();
        Assert.assertFalse(TestUtil.throwException((a)->Maps.checkSafe(map)));
        map.put("1","");
        Assert.assertFalse(TestUtil.throwException((a)->Maps.checkSafe(map)));

    }

    @Test
    public void containsMap() {
        Map<String,Integer> all = Maps.of("a",1,"b",2,"c",3);
        Map<String,Integer> part = Maps.of("a",1,"b",2);

        Assert.assertTrue(Maps.containsMap(all, part));
        part.put("b",3);
        Assert.assertFalse(Maps.containsMap(all, part));

        all.put("b",null);
        part.put("b",null);
        Assert.assertTrue(Maps.containsMap(all, part));
    }

    @Test
    public void getSubMap() {
        Map<String,Integer> all = Maps.of("a",1,"b",2,"c",3);
        Map<String, Integer> subMap = Maps.getSubMap(all, Arrays.asList("a", "b"));
        Assert.assertTrue(Maps.containsMap(all, subMap));
    }

    @Test
    public void mapKey() {
        Map<String,String> all = Maps.of("1","1");
        Map<Integer, String> ret = Maps.mapKey(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1).equals("1"));
    }

    @Test
    public void mapValue() {
        Map<String,String> all = Maps.of("1","1");
        Map<String,Integer> ret = Maps.mapValue(all, (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get("1")==1);
    }

    @Test
    public void mapKeyValue() {
        Map<String,String> all = Maps.of("1","1");
        Map<Integer,Integer> ret = Maps.mapKeyValue(all, (a) -> Integer.parseInt(a), (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.get(1)==1);
    }

    @Test
    public void filterByKey() {
        Map<String,String> all = Maps.of("a","1","b","1");
        Assert.assertTrue(Maps.filterByKey(all, a -> a.equals("a")).size()==1);
        Assert.assertTrue(Maps.filterByKey(all, a -> a.equals("c")).size()==0);
    }

    @Test
    public void filterByValue() {
        Map<String,String> all = Maps.of("a","1","b","2");
        Assert.assertTrue(Maps.filterByValue(all, a -> a.equals("1")).size()==1);
        Assert.assertTrue(Maps.filterByValue(all, a -> a.equals("3")).size()==0);
    }

    @Test
    public void filterByKeyValue() {
        Map<String,String> all = Maps.of("a","1","b","2");
        Assert.assertTrue(Maps.filterByKeyValue(all, (a,b)-> a.equals("a") && b.equals("1")).size()==1);
        Assert.assertTrue(Maps.filterByKeyValue(all, (a,b)-> a.equals("a") && b.equals("2")).size()==0);
    }

    @Test
    public void of() {
        Assert.assertTrue(Maps.of(1,1).size()==1);
        Assert.assertTrue(Maps.of(1,"",2,"").size()==2);
        Assert.assertTrue(Maps.of(1,"",2,"", 3,"").size()==3);
    }

    @Test
    public void zip() {
        HashMap<String, Integer> zip = Maps.zip(Arrays.asList("a", "b"), Arrays.asList(1, 2), HashMap::new);
        Assert.assertTrue(zip.size()==2);
        Assert.assertTrue(zip.get("a")==1);
        Assert.assertTrue(zip.get("b")==2);
    }
}