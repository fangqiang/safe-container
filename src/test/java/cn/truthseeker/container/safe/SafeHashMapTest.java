package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeHashMapTest {

    @Test
    public void init() {
        HashMap map = new HashMap();
        map.put(null,null);
        Assert.assertTrue(TestUtil.throwException((a)-> new SafeHashMap(map)));

        map.clear();
        map.put("","");
        Assert.assertFalse(TestUtil.throwException((a)-> new SafeHashMap(map)));

        new SafeHashMap(1);
        new SafeHashMap(1,0.5f);
    }

    @Test
    public void put() {
        SafeHashMap hashMap = new SafeHashMap();
        Assert.assertTrue(TestUtil.throwException((a)-> hashMap.put(null,"")));
        Assert.assertTrue(TestUtil.throwException((a)-> hashMap.put("",null)));

        Assert.assertFalse(TestUtil.throwException((a)-> hashMap.put("","")));
        Assert.assertFalse(TestUtil.throwException((a)-> hashMap.put("","")));
    }

    @Test
    public void putAll() {
        HashMap map = new HashMap();
        map.put(null,null);

        SafeHashMap hashMap = new SafeHashMap();
        Assert.assertTrue(TestUtil.throwException((a)-> hashMap.putAll(map)));

        map.clear();
        map.put("","");
        Assert.assertFalse(TestUtil.throwException((a)-> hashMap.putAll(map)));
    }
}