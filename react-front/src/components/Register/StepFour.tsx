import { Row, Col, Container } from 'react-bootstrap';

export default function StepFour() {
  return (
    <>
      <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-8">
        Please enter your address
        <form className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <input type="text" placeholder="login" className="rounded-2 m-1"/>
            <input type="text" placeholder="password" className="rounded-2 m-1"/>
            <input type="text" placeholder="repeat password" className="rounded-2 m-1"/>
          </Col>
          <Row className="d-flex justify-content-around">
            <button type="button" className="btn btn-primary col-xxl-6 col-5 mt-3">Next</button> 
            <button type="button" className="btn btn-primary col-xxl-6 col-5 mt-3">Back</button> 
          </Row>
        </form>
        <a href="#" className="text-white row d-flex justify-content-center mt-2">Back to homepage</a>
      </Container>
    </div>
      
    </>
  );
}
