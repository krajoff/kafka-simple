package com.example.consumer.utils;

import com.example.common.models.Message;
import com.example.consumer.models.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Mapping(target = "id", ignore = true)
    public abstract MessageEntity messageToMessageEntity(Message message);

    public abstract Message messageEntityToMessage(MessageEntity messageEntity);

}
