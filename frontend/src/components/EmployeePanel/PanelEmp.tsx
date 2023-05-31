import { Link } from "react-router-dom";
import { useState } from "react";
import { Container, Row } from "react-bootstrap";

import Verification from "./Verification";
import AddAnnouncement from "./AddAnnouncement";
import DeleteAnnouncement from "./DeleteAnnouncement";
import { States } from "./States";

export default function PanelEmp() {
  const [state, setState] = useState(States.Default);

  function handleClick(stateId: number) {
    console.log(States.Default, States.Register, States.Verify, States.AddAnnouncement, States.DeleteAnnouncement);
    console.log(stateId);

    setState(stateId);
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
              <Row id="user-name"> Stan Konwalski </Row>
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
                <Link
                  to="/"
                  type="button"
                  className="btn btn-primary col-xl-2 col-lg-3 col-12 btn-lg rounded-4"
                >
                  Log out
                </Link>
              </Row>
            </Container>
          )}
        </div>
      </div>
    </>
  );
}
