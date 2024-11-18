package com.example.consumer.utils;

import com.example.common.models.Message;
import com.example.consumer.models.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    public abstract MessageEntity messageToMessageEntity(Message message);

    @Mapping(target = "id", ignore = true)
    public abstract Message messageEntityToMessage(MessageEntity messageEntity);

}
