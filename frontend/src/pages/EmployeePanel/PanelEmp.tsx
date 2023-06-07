import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { Container, Row } from "react-bootstrap";
import * as endpoints from "../../endpoints/endpoints";
import Verification from "./Verification";
import AddAnnouncement from "./AddAnnouncement";
import DeleteAnnouncement from "./DeleteAnnouncement";
import { States } from "./States";
import axios, { HttpStatusCode } from 'axios';

export default function PanelEmp() {
  const navigate = useNavigate();
  const username = localStorage.getItem("fullName") || "";
  const userId = localStorage.getItem("userId") || "";
  const token = localStorage.getItem("jwt") || "";
  const [state, setState] = useState(States.Default);

  function handleClick(stateId: number) {
    console.log(States.Default, States.Register, States.Verify, States.AddAnnouncement, States.DeleteAnnouncement);
    console.log(stateId);
    setState(stateId);
  }

  async function logout() {
    try{
    const config = {
      headers: { Authorization: `Bearer ${token}` }
    };
    const response = await axios.post(`${endpoints.LOGOUT_ENDPOINT}`,
     {token: token},
     config
     );
    if(response.status === 200)
      navigate("/");
    } catch(error) {

    }
  }

  return (
    <>
      <div className="d-flex align-items-center vh-100">
        <div className="container text-light col-lg-7 col-8 h-75 d-flex align-items-center">
          {state === States.Verify && <Verification setStateInPanel={handleClick} />}
          {state === States.AddAnnouncement && <AddAnnouncement setStateInPanel={handleClick} />}
          {state === States.DeleteAnnouncement && <DeleteAnnouncement setStateInPanel={handleClick} />}

          {state === States.Default && (
            <Container
              id="panel-container"
              className="
              background-color-container 
              py-3 px-4 rounded-5 
              border border-white border-1 
              d-flex flex-column justify-content-between 
              h-auto"
              >
              <Row id="user-name"> {username} </Row>
              <Container id="buttons-container" className="d-flex flex-column">
                <Row className="mt-3 justify-content-evenly">
                  <Link
                    to="/register"
                    type="button"
                    className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                  >
                    Register client
                  </Link>
                  <button
                    onClick={() => handleClick(States.Verify)}
                    type="button"
                    className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                  >
                    Verify clients
                  </button>
                </Row>
                <Row className="mt-3 justify-content-evenly">
                  <button
                    onClick={() => handleClick(States.AddAnnouncement)}
                    type="button"
                    className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                  >
                    Add announcement
                  </button>
                  <button
                    onClick={() => handleClick(States.DeleteAnnouncement)}
                    type="button"
                    className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                  >
                    Delete announcement
                  </button>
                </Row>
              </Container>
              <Row className="mt-3 d-flex align-content-end">
                <button
                  onClick={() => logout()}
                  type="button"
                  className="btn btn-primary col-xl-2 col-lg-3 col-12 btn-lg rounded-4"
                >
                  Log out
                </button>
              </Row>
            </Container>
          )}
        </div>
      </div>
    </>
  );
}
