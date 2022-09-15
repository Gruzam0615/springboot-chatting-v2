import { Col, Container, Row, InputGroup, Form} from 'react-bootstrap';
import { UseTitle } from '../UseTitle';
import './Main.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import { Outlet, Link } from 'react-router-dom';

function Main() {
  UseTitle("채팅방 목록");
  const [ chatRoomTitleValue, setChatRoomTitleValue ] = useState("");
  const [ chatRoomList, setChatRoomList ] = useState([]);
  
  const onChangeChatRoomTitleValue = (e) => {
    setChatRoomTitleValue(e.target.value);
  }

  const onClickChatRoomCreateBtn = () => {
    // alert(chatRoomTitleValue);
    if(chatRoomTitleValue == "") {
      alert("채팅방 제목을 입력해주세요.");
    }
    else {
      requestCreateChatRoom(chatRoomTitleValue);
      setChatRoomTitleValue("");
      requestSelectChatRoomList();
    }    
  }

  const requestCreateChatRoom = (roomName) => {
    const RequestURL = `/chat/createChatRoom?roomName=${roomName}&usersName=admin`
    const option = {
      method: "POST"
    }
    fetch(RequestURL, option)
      .then(response => response.json())
      .then(data => console.log(data))

  }

  const requestSelectChatRoomList = async() => {
    const RequestURL = `/chat/selectAllChatRoomFromDB`
    const option = {
      method: "GET"
    }
    fetch(RequestURL, option)
      .then(response => response.json())
      .then(data => {
        setChatRoomList(data);
        console.log("requestSelectChatRoomList");
        console.log(chatRoomList);
      })
  }

  useEffect(() => {
    requestSelectChatRoomList();
  }, []);

  return (
    <div className="Main">
      <h1>채팅방 목록</h1>
      <Outlet />
      <Container>
      <Row>
        <Col md={12}>
          <InputGroup>
            <Form.Control
              placeholder="채팅방 제목"
              aria-label={chatRoomTitleValue}
              aria-describedby="ChatRoomTitle"
              value={chatRoomTitleValue}
              onChange={onChangeChatRoomTitleValue}
            />
            <InputGroup.Text id="ChatRoomCreateBtn" onClick={onClickChatRoomCreateBtn}>만들기</InputGroup.Text>
            {/* <InputGroup.Text id="SelectChatRoomListBtn" onClick={requestSelectChatRoomList}>조회</InputGroup.Text> */}
          </InputGroup>
        </Col>
      </Row>
      </Container>
      <div>
        <ul>
        {
          chatRoomList.length == 0 ? 
          <li id='1' key={1}>채팅방이 없습니다.</li> :
          chatRoomList.map((item, index) =>
            <li id={index} key={index}>
              <Link to={`/chatRoom/${item.roomId}`}>{item.roomName}</Link>              
            </li> 
          )
        }
        </ul>
      </div>    
    </div>
  );
}

export default Main;
