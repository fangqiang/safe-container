package cn.truthseeker.container.safe;

import cn.truthseeker.container.util.Emptys;

import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneEmptyMapOper<K, V> extends CommonMapOper<K, V> {

    default boolean putIgnoreEmpty(K key, V value) {
        if(Emptys.isNoneEmpty(key, value)){
            put(key, value);
            return true;
        }else{
            return false;
        }
    }

    default void putAllIgnoreEmpty(Map<K,V> map) {
        map.forEach(this::putIgnoreEmpty);
    }
}
