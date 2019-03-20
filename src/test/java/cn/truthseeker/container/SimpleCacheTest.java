package cn.truthseeker.container;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/19
 */
public class SimpleCacheTest {

    @Test
    public void put() {
        SimpleCache<String,Integer> cache = new SimpleCache();
        cache.put("a",1);
        Assert.assertTrue(cache.getNullable("a").get()==1);
        Assert.assertTrue(cache.get("a")==1);

        Assert.assertTrue(cache.getOrCreate("b", ()-> 2)==2);
        Assert.assertTrue(cache.getOrCreate("b",this::throwException)==2);

        Assert.assertTrue(TestUtil.throwException(()->cache.getOrCreate("c",this::throwException)));
    }

    private int throwException(){
        throw new RuntimeException();
    }
}