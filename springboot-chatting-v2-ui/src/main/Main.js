import { Col, Container, Row, InputGroup, Form} from 'react-bootstrap';
import { UseTitle } from '../UseTitle';
import './Main.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';

function Main() {
  UseTitle("채팅방 목록");
  const [ chatRoomTitleValue, setChatRoomTitleValue ] = useState("");
  const [ chatRoomList, setChatRoomList ] = useState(null);
  
  const onChangeChatRoomTitleValue = (e) => {
    setChatRoomTitleValue(e.target.value);
  }

  const onClickChatRoomCreateBtn = () => {
    // alert(chatRoomTitleValue);
    requestCreateChatRoom(chatRoomTitleValue);
    setChatRoomTitleValue("");
    requestSelectChatRoomList();
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
  }, []);

  return (
    <div className="Main">
      <h1>채팅방 목록</h1>
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
          </InputGroup>
        </Col>
      </Row>
      <Row>
        <Col md={12}>
        {
          chatRoomList != null ?
            chatRoomList.map((item, index) => {
              <span>{item.roomName}</span>
            })
            :
            <span>대화방이 없습니다.</span>
        }  
        </Col>
      </Row>
      </Container>         
    </div>
  );
}

export default Main;
