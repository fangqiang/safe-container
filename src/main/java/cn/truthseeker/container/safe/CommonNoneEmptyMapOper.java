package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneEmptyMapOper<K, V> extends CommonMapOper<K, V> {

    /**
     * 如果key和value都不为empty，put成功，返回true
     * 否则，忽略，返回false
     */
    default boolean putIfNotEmpty(K key, V value) {
        if (Emptys.isNoneEmpty(key, value)) {
            put(key, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * putAll，忽略map中key为empty或者value为empty的键值对
     */
    default void putAllOmitEmpty(Map<K, V> map) {
        map.forEach(this::putIfNotEmpty);
    }
}
