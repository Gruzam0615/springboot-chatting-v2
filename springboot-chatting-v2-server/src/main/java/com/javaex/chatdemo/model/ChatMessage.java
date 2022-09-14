package com.javaex.chatdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ChatMessage")
public class ChatMessage {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageIdx;
    @Column private MessageType messageType;
    @Column private String roomId;   
    @Column private String sender;
    @Column private String message;
    @Column private String fileMessage;
    @Column private String fileName;
    @Column private String sendDate;

}
