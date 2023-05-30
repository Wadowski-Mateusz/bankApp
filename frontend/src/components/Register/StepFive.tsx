import { useState } from 'react';
import { Container } from 'react-bootstrap';
import { Link } from "react-router-dom";


interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}



export default function StepFive( { move, stepId }: Props ) {

  const [clientRole, setclientRole] = useState(true);
 


  return (
    <>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container 
        className="
          d-flex justify-content-center 
          col-lg-3 col-md-4 col-8 
          fs-md-5 fs-3 
          text-white">
      <form className="row d-flex justify-content-center">
        { clientRole &&
          <>
            <span className='badge text-wrap'>
              Please, wait for our consultant to verify Your account. We will send You an email when this is done.
            </span>
            <Link 
              type="button" 
              className="btn btn-primary mt-3 fs-4 p-3 rounded-4"
              to="/">
                Take me back to homepage
            </Link>
          </>
        }
        { !clientRole &&
          <>
            <span className='badge text-wrap'>
              Client account has been created!
            </span>
            <Link 
              type="button" 
              className="btn btn-primary mt-3 fs-4 p-3 rounded-4"
              to="/">
                Go back to panel
            </Link>
          </>
        }
        </form>
      </Container>
    </div>
    </>
  );
}
