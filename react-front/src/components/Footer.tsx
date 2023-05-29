import { Row, Col } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

export default function Footer() {
  return (
    <Navbar bg="primary" expand="lg" className="fixed-bottom">
    <Container>
      <Navbar.Toggle/>
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="d-flex flex-row col-12">
            <Col  className="d-flex flex-column justify-content-center">
                <span className="text-dark">Phone number:</span>
                <span className="text-dark">000-000-000</span>
                <span className="text-dark">10AM - 6PM Mon - Fri</span>
            </Col>

            <Col className="d-flex flex-column justify-content-center">
                <span className="text-dark">Address:</span>
                <span className="text-dark">Cracow, Streeeeet 12</span>
                <span className="text-dark">00-000, Poland</span>
            </Col>

            <Col className="d-flex align-items-center">
              <Row>
                <span className="text-dark">support@marsupium.com</span>
              </Row>
            </Col>
        </Nav>
      </Navbar.Collapse>
    </Container>
  </Navbar>
  );  

}
