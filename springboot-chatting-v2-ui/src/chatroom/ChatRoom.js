import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";

import * as StompJs from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs";

const ChatRoom = (param) => {
    const client = useRef({});
    const currentOriginURL = window.location.href;
    const currentURL = new URL(currentOriginURL);
    
    const [ roomId, setRoomId ] = useState(currentURL.pathname.split("/")[2]);

    const [ messages, setMessages ] = useState([]);
    const [ message, setMessage ] = useState("");

    const onChangeMessage = (e) => {
        setMessage(e.target.value);
    }
    
    const sendMessageBtn = () => {
        setMessage("");
    }

    const enterChatRoom = (roomId) => {
        const requestURL = `/chat/room/enter/${roomId}?usersName=Admin01&messageType=ENTER`
        fetch(requestURL)
            .then(response => response.text());
    }

    useEffect(() => {
      
    },[])

    const connect = () => {
        client.current = new StompJs.Client({
            webSocketFactory: () => new SockJS("/ws-stomp/chat"),
            debug: (frame) => {
                console.log("debug");
                console.log(frame);
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
            onConnect: () => {
                subscribe();
            },
            onStompError: (frame) => {
                console.error("STOMP ERROR");
                console.error(frame);
            },
        });
        client.current.activate();
    };

    const disconnect = () => {
        client.current.deactivate();
    };

    const subscribe = () => {
        client.current.subscribe(`/topic/chat/${roomId}`, ({ body }) => {
            setMessages((msgs) => [...msgs, JSON.parse(body)]);
        });
    };

    const publish = (msg) => {
        if(!client.current.connected) {
            return;
        }

        client.current.publish({
            destination: "/app/chat",
            body: JSON.stringify({
                roomId: {roomId},
                sender: "Admin01",
                message: {msg},
                sendDate: ""
            })
        });
    }

    return(
        <div>
            <h1>채팅방 RoomId: {roomId}</h1>
            <ul></ul>
            <input type={"text"} value={message} onChange={onChangeMessage} />
            <input type={"button"} value="보내기" onClick={sendMessageBtn}/>
        </div>
    );
}
export default ChatRoom