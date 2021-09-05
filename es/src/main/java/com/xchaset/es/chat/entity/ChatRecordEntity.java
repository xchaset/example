package com.xchaset.es.chat.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "chat",createIndex = true)
@ToString
public class ChatRecordEntity {

    @Id
    private String id;

    private String fromId;

    private String fromName;

    private String toId;

    private String toName;

    private long timestamp;

    private String msg;

    private String isGroup;

    private String groupName;

    private String groupId;

    private String msgType;

    private List<String> groupMembers;



}
