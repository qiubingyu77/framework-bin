package com.bin.framework.redis.ops;

import org.redisson.api.mapreduce.RCollectionMapReduce;

import java.util.List;

/**
 * @autor qiubingyu
 * @ClassName ListOps
 * @date 2020/12/17
 **/
public interface ListOps<V> {

    List<V> get(String key, int... index);

    <KOut, VOut> RCollectionMapReduce<V, KOut, VOut> mapReduce(String key);

    int addAfter(String key, V indexVal, V nextVal);

    int addBefore(String key, V indexVal, V preVal);

    void fastSet(String key, int index, V val);

    List<V> subList(String key, int index1, int index2);

    List<V> readAll(String key);

    void trim(String key, int index1, int index2);

    void fastRemove(String key, int index);

    boolean remove(String key, V val, int indx);

}
