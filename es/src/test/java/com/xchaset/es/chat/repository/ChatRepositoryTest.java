package com.xchaset.es.chat.repository;

import com.xchaset.es.chat.entity.ChatRecordEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ChatRepositoryTest {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Test
    public void testSave(){
        ChatRecordEntity chatRecordEntity = new ChatRecordEntity();
        chatRecordEntity.setId("2");
        chatRecordEntity.setFromId("chase1");
        chatRecordEntity.setFromName("chase1");
        chatRecordEntity.setToId("shasha1");
        chatRecordEntity.setToName("shashababy1");
        chatRecordEntity.setIsGroup("0");
        chatRecordEntity.setMsgType("text");
        chatRecordEntity.setMsg("hi! this is the chat_09 index");
        chatRecordEntity.setTimestamp(new Date().getTime());
        // 指定索引，指定索引后会忽略@Document注解上指定的indexName
        ChatRecordEntity save = elasticsearchRestTemplate.save(chatRecordEntity, IndexCoordinates.of("chat_09"));
        System.out.println(save);
    }

    @Test
    public void testSave2(){
        ChatRecordEntity chatRecordEntity = new ChatRecordEntity();
        chatRecordEntity.setId("3");
        chatRecordEntity.setFromId("chase");
        chatRecordEntity.setFromName("chase");
        chatRecordEntity.setToId("shasha");
        chatRecordEntity.setToName("shashababy");
        chatRecordEntity.setIsGroup("0");
        chatRecordEntity.setMsgType("text");
        chatRecordEntity.setMsg("hi!");
        chatRecordEntity.setTimestamp(new Date().getTime());
        ChatRecordEntity save = chatRepository.save(chatRecordEntity);

    }

    @Test
    public void testSearch(){
        Criteria criteria = new Criteria("msg");
//        criteria.is("love you forever!");
//        criteria.is("forever");
        criteria.matches("1212");
        criteria.and("fromName").is("chase");
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<ChatRecordEntity> search = elasticsearchOperations.search(criteriaQuery, ChatRecordEntity.class);
        List<ChatRecordEntity> collect = search.stream().map(entry -> entry.getContent()).collect(Collectors.toList());
        collect.forEach(a -> {
            System.out.println(a.getMsg());
        });
    }

    @Test
    public void testSearch2(){
        Page<ChatRecordEntity> all = chatRepository.findAll(PageRequest.of(0, 10));
        Stream<ChatRecordEntity> chatRecordEntityStream = all.get();
        chatRecordEntityStream.collect(Collectors.toList()).forEach(e -> {
            System.out.println(e.getMsg());
        });

    }

    @Test
    public void testSearchSimilar(){
        ChatRecordEntity chatRecordEntity = new ChatRecordEntity();
        chatRecordEntity.setMsg("love");
        chatRecordEntity.setId("100");
        Page<ChatRecordEntity> chatRecordEntities = chatRepository.searchSimilar(chatRecordEntity, new String[]{"msg"}, PageRequest.of(0, 10));
        chatRecordEntities.get().forEach(e -> {
            System.out.println(e.getMsg());
        });
    }

    @Test
    public void testNavitiveSearch(){
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("love"))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(new HighlightBuilder.Field("msg"))
//                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .build();
        SearchHits<ChatRecordEntity> search = elasticsearchRestTemplate.search(build, ChatRecordEntity.class);
        search.forEach(e -> {
            System.out.println(e.getContent().getMsg());
            System.out.println(e.toString());
        });
    }


    @Test
    public void testAlias(){
        AliasActions aliasActions = new AliasActions();
        AliasAction aliasAction = new AliasAction.Add(AliasActionParameters.builder().withAliases("chat_09_05").withIndices("chat").build());

        aliasActions.add(aliasAction);
        elasticsearchOperations.indexOps(ChatRecordEntity.class).alias(aliasActions);

    }
}

