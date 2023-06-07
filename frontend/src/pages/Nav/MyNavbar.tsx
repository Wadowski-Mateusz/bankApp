import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link, useNavigate } from "react-router-dom"
import axios from 'axios';
import * as endpoints from "../../endpoints/endpoints";

function MyNavbar() {
  const token = localStorage.getItem("jwt") || "";
  const navigate = useNavigate();

  async function logout() {
    try{
      const config = {
        headers: { Authorization: `Bearer ${token}` }
      };
      const response = await axios.post(
        `${endpoints.LOGOUT_ENDPOINT}`,
        {token: token},
        config
      );
    if(response.status === 200) {
      // navigate("/");
    }
    } catch(error) {

    }
  }

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
            {/* <button className="nav-link" onClick={() => logout()}>Logout</button> */}
            <Link className="nav-link" to="/" onClick={() => logout()}>Logout</Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;



