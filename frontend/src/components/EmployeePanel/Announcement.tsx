import { Container, Row, Col, Form } from "react-bootstrap";


export default function Announcement() {

  function handleClick() {
    // delete from database and display
  }

  return(
    <Container className="d-flex flex-column justify-content-center align-content-center border rounded-4 my-3">
    <Form className="gap-1 row d-flex flex-row justify-content-center align-items-center">
      <Col className="col-10">
        <Row>
          <Col>From: 1234-20-12</Col>
          <Col>To: 1234-20-12</Col>
        </Row>
        <Row className="border border-1 border-info rounded-4 mb-1">
          <Col className="overflow-y-auto" style={{maxHeight: 150 }}>
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Illo nobis voluptas atque deserunt libero nisi, sit veritatis enim odio explicabo?
          </Col>
        </Row>
      </Col>

      <Col type="button" className="col-1 text-danger h1 me-1" onClick={handleClick}>
        X
      </Col>
    </Form>
  </Container>
  )
}