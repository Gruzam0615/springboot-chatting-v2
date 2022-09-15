package com.javaex.chatdemo.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.chatdemo.model.ChatMessage;
import com.javaex.chatdemo.model.MessageType;
import com.javaex.chatdemo.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {
    /*
    @MessageMapping()의 경로가 "/chat/message"이지만 ChatConfig의 setApplicationDestinationPrefixes()를 통해 
    prefix를 "/app"으로 해줬기 때문에 실질 경로는 "/app/chat/message"가 됨
    클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면,
    메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 됨.
    */
    
    private final SimpMessageSendingOperations sendingOperations;
    
    @Autowired private ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if((message.getMessageType()).equals(MessageType.ENTER)) {
            message.setMessage(message.getSender() + "님이 입장했습니다.");
            log.info(message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }

        if((message.getMessageType()).equals(MessageType.TALK)) {
            log.info(message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }

        if((message.getMessageType()).equals(MessageType.FILE)) {
            log.info(message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }

        // if(ChatMessage.MessageType.LEAVE.equals(message.getMessageType())) {
        if((message.getMessageType()).equals(MessageType.LEAVE)) {
            message.setMessage(message.getSender() + "님이 퇴장했습니다.");
            log.info(message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }    

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/chat/message/read")
    public List<ChatMessage> readMessageList() {
        return chatMessageService.readMessagesList();
    }

    // @GetMapping("/chat/message/readByRoomId/{roomId}")
    // public List<ChatMessage> readByRoomId(@PathVariable String roomId) {
    //     return chatMessageService.readMessagesListByRoomId(roomId);
    // }

    @GetMapping("/chat/message/readByRoomId/{roomId}")
    public List<ChatMessage> readByRoomId(@PathVariable String roomId) {
        return chatMessageService.readMessagesListByRoomIdFromDB(roomId);
    }

}
