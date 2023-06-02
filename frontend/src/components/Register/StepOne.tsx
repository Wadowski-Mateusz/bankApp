import React, { ChangeEvent, useContext } from "react";
import { Col, Container } from 'react-bootstrap';
import { Link } from "react-router-dom";

import { RegisterDataContext } from "./Register";
import HomeHyperlink from './HomeHyperlink'
import InputField from "./InputField";

import DatePicker from "react-datepicker";

interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
  
}


export default function StepOne( { move, stepId }: Props ) {
  const { registerData, setRegisterData } = useContext(RegisterDataContext);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // validation
    move(stepId + 1)
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    console.log(name, value)
    setRegisterData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };
  
  return (
    <>
    <span className="h1">STEP 1</span>
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-8">
      <span className='text-white text-center h2 mb-3'> Please enter your data </span>
        <form onSubmit={handleSubmit} className="row d-flex justify-content-center">
          <Col className="d-flex flex-column gap">
          <InputField name="firstName" type="text" value={registerData.firstName} onChange={handleInputChange} placeholder="First Name"/>
          <InputField name="lastName"  type="text" value={registerData.lastName} onChange={handleInputChange} placeholder="Last Name"/>
          {/* <InputField name="Birthday" value={registerData.birthday} onChange={handleInputChange} placeholder="Birthday"/> */}
          {/* <input type="text" placeholder="First Name" className="rounded-2 m-1" /> */}
          <DatePicker 
              selected = {registerData.birthday}
              onChange={(date: Date) => setRegisterData((prevState) => ({
                ...prevState,
                birthday: date
              }))}
              className="rounded-2 m-1 col-12"
              placeholderText="Select birthday"
            />
              
          {/* <input type="text" placeholder="Birthday" className="rounded-2 m-1" /> */}
          </Col>
          <Col className="d-flex flex-column">
            <InputField name="email" type="email" value={registerData.email} onChange={handleInputChange} placeholder="email@example.com"/>
            <button type="button" className="btn btn-sm btn-primary rounded-2 m-1">Add ID scan</button>
            <InputField name="idNumber" type="text" value={registerData.idNumber} onChange={handleInputChange} placeholder="ID number"/>
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
