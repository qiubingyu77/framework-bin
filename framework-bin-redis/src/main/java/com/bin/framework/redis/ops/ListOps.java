package com.bin.framework.redis.ops;

import org.springframework.data.redis.core.ListOperations;

/**
 * @autor qiubingyu
 * @ClassName ListOps
 * @date 2020/12/17
 **/
public interface ListOps<K,V> extends ListOperations<K,V> {
}
