package com.javaex.chatdemo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.chatdemo.model.ChatRoom;
import com.javaex.chatdemo.service.ChatRoomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired private ChatRoomService chatRoomService;

    /**
     * 채팅방을 생성하는 메서드
     * @param roomName
     * @param usersName
     * @return
     */
    @PostMapping("/createChatRoom")
    @ResponseBody
    public ChatRoom createChatRoom(
        @RequestParam("roomName") String roomName,
        @RequestParam("usersName") String usersName
    ) {
        ChatRoom createChatRoom = chatRoomService.createChatRoom(roomName, Long.parseLong("1"));
        return createChatRoom;
    }
    
    /**
     * Spring서버에 존재하는 모든 채팅방을 출력하는 메서드
     * @return
     */
    @GetMapping("/selectAllChatRoomFromServer")
    @ResponseBody
    public List<ChatRoom> selectAllChatRoomFromServer() {
        return chatRoomService.selectAllChatRoomFromServer();
    }
    @GetMapping("/selectAllChatRoomFromDB")
    @ResponseBody
    public List<ChatRoom> selectAllChatRoomFromDB() {
        return chatRoomService.selectAllChatRoomFromDB();
    }
    /**
     * DB에 존재하는 채팅방 목록을 출력하는 메서드(usersIdx가 일치하는)
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectRoomsByUsersIdxFromDB")
    @ResponseBody
    public List<ChatRoom> selectRoomsByUsersIdxFromDB(@RequestParam("usersIdx") Long usersIdx) {
        return chatRoomService.selectRoomsByUsersIdxFromDB(usersIdx);
    }
    @GetMapping("/selectRoomsByUsersIdxFromMvc")
    public String selectRoomsByUsersIdxFromMvc(@RequestParam("usersIdx") Long usersIdx) {
        return "/chat/room";
    }
    /**
     * roomId가 일치하는 채팅방을 삭제하는 메서드
     * @param roomId
     * @return
     */
    @PutMapping("/deleteChatRoomByRoomId")
    @ResponseBody
    public List<ChatRoom> deleteChatRoomByRoomId(
        @RequestParam("roomId") String roomId
    ) {
        return chatRoomService.deleteChatRoomRoomByRoomId(roomId);
    }   

    // roomId에 해당하는 채팅방에 입장
    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public String roomEnterRoomId(
        @PathVariable String roomId,
        @RequestParam(value="usersName") String usersName,
        @RequestParam(value="messageType") String messageType
    ) {
        log.info("## User: {} get in RoomId: {}\n## T: {}", usersName, roomId, LocalDateTime.now());
        return roomId;
    }
    /**
     * 채팅룸 내부에서 채팅룸에 접속을 갱신하는 메서드
     * roomdetail.html findRoom()에서 사용
     * @param roomId
     * @param model
     * @return
     */
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {        
        ChatRoom transaction = chatRoomService.findById(roomId);
        return transaction;
    }

}
