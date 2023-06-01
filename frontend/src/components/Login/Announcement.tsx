import {Container, Row, Col } from "react-bootstrap";

interface Props {
  content: String
}
const myVariable = 'Hello, world!'; // Przykładowa wartość zmiennej
export default function Announcment( {content} : Props) {
  return (
    
    
      <Container>
        <Row className="row justify-content-center mt-2">
          <Col className="text-center background-color-container col-lg-5 col-md-8 p-3 rounded-3 text-light">
            {content}
          </Col>
        </Row>
      </Container>
    
  
  );
}
