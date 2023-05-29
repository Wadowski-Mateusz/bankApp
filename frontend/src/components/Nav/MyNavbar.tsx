import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from "react-router-dom"

function MyNavbar() {
  return (
    <Navbar bg="primary" expand="lg" className="fs-4">
      <Container>
        <Navbar.Brand href="#">[LOGO]</Navbar.Brand>
        <Navbar.Toggle/>
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Link className="nav-link" to="/account">Account</Link>
            <Link className="nav-link" to="/loans">Loans</Link>
            <Link className="nav-link" to="/settings">Settings</Link>
            <Link className="nav-link" to="/">Logout</Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;



