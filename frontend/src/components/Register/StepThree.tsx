import { Row, Col, Container } from 'react-bootstrap';
import HomeHyperlink from './HomeHyperlink';
import { Link } from "react-router-dom";
import React, { useState } from 'react';
import InputField from "./InputField";

interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}



export default function StepThree( { move, stepId }: Props ) {

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

  const [code, setCode] = useState("");
  const [isCodeValid, setIsCodeValid] = useState(false);


  async function handleClickNext() {
      //validation
      setIsCodeValid(true)
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    console.log(name, value)
    if(value.length < 5)
    // check is valid 
    setCode(value);
  };
  
  return (
    <>
    <span className="h1">STEP 3</span>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-lg-4 col-sm-6 col-8">
        <span className='text-white text-center h2 mb-3'> Please, enter code from Your e-mail </span>
        <form onSubmit={handleSubmit} className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <InputField name="code" type="text" value={code} onChange={handleInputChange} placeholder="****"/>
          </Col>
          <Row className="d-flex justify-content-evenly">
            <button name="back" className="btn btn-primary col-xxl-4 col-sm-5 col-12 mt-3">Back</button> 
            <button name="next" className="btn btn-primary col-xxl-4 col-sm-5 col-12 mt-3">Next</button>  
          </Row>
        </form>
        <HomeHyperlink />
      </Container>
    </div>

    </>
  );
}
