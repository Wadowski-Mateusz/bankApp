import { Row, Col, Container } from 'react-bootstrap';

export default function StepThree() {
  return (
    <>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-4">
        Please, enter code from Your e-mail
        <form className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <input type="text" placeholder="******" className="rounded-2 m-1"/>
          </Col>
          <Row className="d-flex justify-content-around">
            <button type="button" className="btn btn-primary col-lg-6 col-12 mt-1">Next</button> 
            <button type="button" className="btn btn-primary col-lg-6 col-12 mt-1">Back</button> 
          </Row>
        </form>
        <a href="#" className="text-white row d-flex justify-content-center mt-2">Back to homepage</a>
      </Container>
    </div>

    </>
  );
}
