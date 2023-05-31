import { Col, Container } from 'react-bootstrap';
import HomeHyperlink from './HomeHyperlink'
import { Link } from "react-router-dom";

interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}


export default function StepOne( { move, stepId }: Props ) {

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // validation
    move(stepId + 1)
  }
  
  return (
    <>
    <span className="h1">STEP 1</span>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-8">
      <span className='text-white text-center h2 mb-3'> Please enter your data </span>
        <form onSubmit={handleSubmit} className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
          <input type="text" placeholder="First Name" className="rounded-2 m-1" />
          <input type="text" placeholder="Last Name" className="rounded-2 m-1" />
          <input type="text" placeholder="Birthday" className="rounded-2 m-1" />
          </Col>
          <Col className="d-flex flex-column">
          <input type="email" placeholder="email" className="rounded-2 m-1" />
          <button type="button" className="btn btn-sm btn-primary rounded-2 m-1">Add ID scan</button>
          <input type="text" placeholder="id number" className="rounded-2 m-1" />
          </Col>
          <button
            className="
              btn btn-primary 
              col-xxl-8 col-6 
              mt-3">
                Next
          </button>  
        </form>
        <HomeHyperlink />

      </Container>
    </div>
    </>
  );
}
