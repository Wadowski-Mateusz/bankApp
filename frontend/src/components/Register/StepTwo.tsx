import { Row, Col, Container } from 'react-bootstrap';
import HomeHyperlink from './HomeHyperlink';
import React from 'react';


interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}



export default function StepTwo( { move, stepId }: Props ) {

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // validation
    const btnName = (e.nativeEvent.submitter as HTMLButtonElement).name;
    if(btnName === "next") {
      move(stepId + 1)
    }  
    else {
      move(stepId - 1)
    }
    
  }
  
  return (
    <>
    <span className="h1">STEP 2</span>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-8">
        <span className='text-white text-center h2 mb-3'> Please enter your Address </span>
        <form onSubmit={handleSubmit} className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <input type="text" placeholder="Country" className="rounded-2 m-1"/>
            <input type="text" placeholder="Sector" className="rounded-2 m-1"/>
            <input type="text" placeholder="City" className="rounded-2 m-1"/>
          </Col>
          <Col className="d-flex flex-column">
            <input type="text" placeholder="Street" className="rounded-2 m-1"/>
            <input type="text" placeholder="Number" className="rounded-2 m-1"/>
            <input type="text" placeholder="Zip" className="rounded-2 m-1"/>
          </Col>
          <Row className="d-flex justify-content-evenly">
            <button type="submit" name="back" className="btn btn-primary col-xxl-4 col-5 mt-3">Back</button> 
            <button type="submit" name="next" className="btn btn-primary col-xxl-4 col-5 mt-3">Next</button> 
          </Row>
        </form>
        <HomeHyperlink />
      </Container>
    </div>

    </>
  );
}
