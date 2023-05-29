import { useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';

export default function StepFive() {

  const [clientRole, setclientRole] = useState(true);


  return (
    <>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-4 text-white">
      <form className="row d-flex justify-content-center">
        { clientRole &&
          <>
            <div id="info">
              Please, wait for our consultant to verify Your account. We will send You an email when this is done.
            </div>
            <button type="button" className="btn btn-primary">
              Take me back to homepage
            </button>
          </>
        }
        { !clientRole &&
          <>
            <div id="info">
              Client account has been created!
            </div>
            <button type="button" className="btn btn-primary">
              Go back to panel
            </button>
          </>
        }
        </form>
      </Container>
    </div>
    </>
  );
}
