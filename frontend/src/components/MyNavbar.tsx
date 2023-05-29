import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

function MyNavbar() {
  return (
    <Navbar bg="primary" expand="lg" className="fs-4">
      <Container>
        <Navbar.Brand href="#home">[LOGO]</Navbar.Brand>
        <Navbar.Toggle/>
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#">Account</Nav.Link>
            <Nav.Link href="#">Loans</Nav.Link>
            <Nav.Link href="#">Panel</Nav.Link>
            <Nav.Link href="#">Settings</Nav.Link>
            <Nav.Link href="#">Logout</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;



