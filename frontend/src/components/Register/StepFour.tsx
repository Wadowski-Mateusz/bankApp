import { Row, Col, Container } from 'react-bootstrap';
import HomeHyperlink from './HomeHyperlink';
import { Link } from "react-router-dom";


interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}



export default function StepFour( { move, stepId }: Props ) {

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // validation
    move(stepId + 1)
  }
  
  return (
    <>
      <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-4">
      <span className='text-white text-center h2 mb-3'> Enter login and password</span>
        <form className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <input type="text" placeholder="login" className="rounded-2 m-1"/>
            <input type="text" placeholder="password" className="rounded-2 m-1"/>
            <input type="text" placeholder="repeat password" className="rounded-2 m-1"/>
          </Col>
          <Row className="d-flex justify-content-evenly">
            <button name="back" className="btn btn-primary col-xxl-4 col-5 mt-3">Back</button> 
            <button name="next" className="btn btn-primary col-xxl-4 col-5 mt-3">Next</button>  
          </Row>
        </form>
        <HomeHyperlink />
      </Container>
    </div>
      
    </>
  );
}
