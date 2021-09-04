package com.xchaset.es.chat.repository;

import com.xchaset.es.chat.entity.ChatRecordEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ChatRepository extends ElasticsearchRepository<ChatRecordEntity,String> {
}
