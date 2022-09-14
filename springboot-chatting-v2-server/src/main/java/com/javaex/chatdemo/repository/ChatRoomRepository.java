package com.javaex.chatdemo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaex.chatdemo.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO ChatRoom(roomId, roomName, usersIdx, companysIdx, dataInfo, createdDate, usable)" + 
        "VALUES(:#{#ChatRoom.roomId}, :#{#ChatRoom.roomName}, :#{#ChatRoom.usersIdx}, :#{#ChatRoom.companysIdx}," +
        ":#{#ChatRoom.dataInfo}, :#{#ChatRoom.createdDate}, :#{#ChatRoom.usable})"
        ,nativeQuery=true
    )
    public int createChatRoom(@Param("ChatRoom") ChatRoom chatRoom);

    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.roomId=?1",
        nativeQuery = true
    )
    public ChatRoom findChatRoomByRoomIdFromDB(String roomId);

    @Query(
        value="SELECT * FROM ChatRoom c ORDER BY c.createdDate DESC",
        nativeQuery = true
    )
    public List<ChatRoom> selectAllChatRoomFromDB();

    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.usersIdx=?1", nativeQuery = true
    )
    public List<ChatRoom> selectRoomsByUsersIdxFromDB(Long usersIdx);

}
