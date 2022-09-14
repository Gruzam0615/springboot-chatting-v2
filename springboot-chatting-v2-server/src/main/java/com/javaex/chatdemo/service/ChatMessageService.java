package com.javaex.chatdemo.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.javaex.chatdemo.model.ChatMessage;
import com.javaex.chatdemo.repository.ChatMessageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatMessageService implements ChatMessageRepository {
    
    private Map<String, Object> chatMessages = new HashMap<>();
    private static List<ChatMessage> chatMessagesList = new LinkedList<>();
    @Autowired private ChatMessageRepository chatMessageRepository;

    public void addMessage(ChatMessage chatMessage) {
        chatMessagesList.add(chatMessage);
    }

    public List<ChatMessage> readMessagesList() {
        return chatMessagesList;
    }

    public List<ChatMessage> readMessagesListByRoomId(String roomId) {
        List<ChatMessage> roomChatMessagesList = new LinkedList<>();
        for(int i = 0; i < chatMessagesList.size(); i++) {
            if(chatMessagesList.get(i).getRoomId().equals(roomId)) {
                roomChatMessagesList.add(chatMessagesList.get(i));
            }
        }
        // Gson gsonObject = new Gson();
        // String result1 = gsonObject.toJson(roomChatMessagesList);
        // log.info("gson: {}", result1);

        return roomChatMessagesList;
    }

    @Override
    public <S extends ChatMessage> S save(S entity) {
        return chatMessageRepository.save(entity);
    }

    @Override
    public List<ChatMessage> readMessagesListByRoomIdFromDB(String roomId) {
        List<ChatMessage> roomChatMessagesListFromDB = chatMessageRepository.readMessagesListByRoomIdFromDB(roomId);
        
        // Gson gsonObject = new Gson();
        // String result1 = gsonObject.toJson(roomChatMessagesList);
        // log.info("gson: {}", result1);

        return roomChatMessagesListFromDB;

    }

    @Override
    public List<ChatMessage> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatMessage> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatMessage> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatMessage> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatMessage> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ChatMessage getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatMessage getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatMessage getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ChatMessage> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ChatMessage> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(ChatMessage entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends ChatMessage> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatMessage> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends ChatMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatMessage> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends ChatMessage> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends ChatMessage, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
