package com.bin.framework.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IndexRepository extends ElasticsearchRepository<Object, String> {
}
