package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ListOps;
import org.redisson.api.RList;
import org.redisson.api.mapreduce.RCollectionMapReduce;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @autor qiubingyu
 * @ClassName ListOpsImpl
 * @date 2020/12/23
 **/
@Component
class ListOpsImpl<V> extends AbstrackRedisOps implements ListOps<V> {
    @Override
    public List<V> get(String key, int... index) {
        final RList<V> list = redissonClient.getList(key);
        return list.get(index);
    }

    @Override
    public <KOut, VOut> RCollectionMapReduce<V, KOut, VOut> mapReduce(String key) {
        final RList<V> list = redissonClient.getList(key);
        return list.mapReduce();
    }

    @Override
    public int addAfter(String key, V indexVal, V nextVal) {
        final RList<V> list = redissonClient.getList(key);
        return list.addAfter(indexVal, nextVal);
    }

    @Override
    public int addBefore(String key, V indexVal, V preVal) {
        final RList<V> list = redissonClient.getList(key);
        return list.addBefore(indexVal, preVal);
    }

    @Override
    public void fastSet(String key, int index, V val) {
        final RList<V> list = redissonClient.getList(key);
        list.fastSet(index, val);
    }

    @Override
    public List<V> subList(String key, int index1, int index2) {
        final RList<V> list = redissonClient.getList(key);
        return list.subList(index1, index2);
    }

    @Override
    public List<V> readAll(String key) {
        final RList<V> list = redissonClient.getList(key);
        return list.readAll();
    }

    @Override
    public void trim(String key, int index1, int index2) {
        final RList<V> list = redissonClient.getList(key);
        list.trim(index1, index2);
    }

    @Override
    public void fastRemove(String key, int index) {
        final RList<V> list = redissonClient.getList(key);
        list.fastRemove(index);
    }

    @Override
    public boolean remove(String key, V val, int indx) {
        final RList<V> list = redissonClient.getList(key);
        return list.remove(val, indx);
    }
}
