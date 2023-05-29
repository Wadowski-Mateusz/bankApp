import { Row, Col, Container } from 'react-bootstrap';

export default function StepOne() {
  return (
    <>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-8">
        <form className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
          <input type="text" placeholder="First Name" className="rounded-2 m-1" />
          <input type="text" placeholder="Last Name" className="rounded-2 m-1" />
          <input type="text" placeholder="Birthday" className="rounded-2 m-1" />
          </Col>
          <Col className="d-flex flex-column">
          <input type="text" placeholder="email" className="rounded-2 m-1" />
          <button type="button" className="btn btn-sm btn-primary rounded-2 m-1">Add ID scan</button>
          <input type="text" placeholder="id number" className="rounded-2 m-1" />
          </Col>
          <button type="button" className="btn btn-primary col-xxl-8 col-6 mt-3">Next</button>  
        </form>
        <a href="#" className="text-white row d-flex justify-content-center mt-2">Back to homepage</a>
      </Container>
    </div>
    </>
  );
}
