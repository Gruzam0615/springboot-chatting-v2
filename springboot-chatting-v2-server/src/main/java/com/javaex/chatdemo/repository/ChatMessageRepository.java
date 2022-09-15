package com.javaex.chatdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaex.chatdemo.model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(value="SELECT * FROM ChatMessage c WHERE c.roomId = ?1 ORDER BY c.sendDate ASC", nativeQuery = true)
    public List<ChatMessage> readMessagesListByRoomIdFromDB(String roomId);
    
}
