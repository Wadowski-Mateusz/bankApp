import { Row, Col, Container, Alert } from 'react-bootstrap';
import HomeHyperlink from './HomeHyperlink';
import { Link } from "react-router-dom";
import React, { ChangeEvent, useContext, useState } from "react";
import { RegisterDataContext } from "./Register";
import InputField from "./InputField";
import axios from 'axios';
import * as Endpoint from '../../endpoints/endpoints';
import { RegisterDTO } from '../DTOs/RegisterDTO';

interface Props {
  move: (moveTo: number) => void,
  stepId: number, 
}



export default function StepFour( { move, stepId }: Props ) {
  const { registerData, setRegisterData, idScan } = useContext(RegisterDataContext);

  const [repeatedPassword, setRepeatedPassword] = useState("");
  const RepeatedPasswordName = "repeatedPassword";
  
  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const btnName = (e.nativeEvent.submitter as HTMLButtonElement).name;

    if(btnName === "back") {
      move(stepId - 1)
    } else if(btnName === "next") {
      try {
        const formData = new FormData();
        formData.append('idScan', idScan);
        formData.append('registerDTO', JSON.stringify(registerData));
          const response = await axios.post(Endpoint.REGISTER_USER, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        console.log(response.data)
        if(response.status === 200)  {
          move(stepId + 1)
        }

      } catch (error) {
        // console.error('error:', error);
        // Handle error here
      }
    }
  }
  
  // async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
  //   e.preventDefault()
  //   // validation
  //   const btnName = (e.nativeEvent.submitter as HTMLButtonElement).name;

  //   if(btnName === "back") {
  //     move(stepId - 1)
  //   } else if(btnName === "next") {
  //     if(repeatedPassword !== registerData.password) {

  //     }
  //     try {
  //       // setRegisterData((prevState) => ({
  //       //   ...prevState,
  //       //   idURI: "/dev/null"
  //       // }));

  //       // const formData = new FormData();
  //       // formData.append('image', idScan!!);
  //       // console.log(registerData, idScan);
  //       // const response = await axios.post(REGISTER_USER, registerData, formData);

  //       // ...
  //       const formData = new FormData();
  //       formData.append('image', idScan!!);
  //       formData.append('registerDTO', new Blob([JSON.stringify(registerData)], { type: 'application/json' }));
        
  //       const config = {
  //         headers: {
  //           'Content-Type': 'multipart/form-data'
  //         }
  //       };
        
  //       const response = await axios.post(REGISTER_USER, formData, config);
  //       // ...



  //       console.log(response.data);
  //       move(stepId + 1)  
  //     } catch (error) {
  //       console.error('fetch error:', error);
  //     } 
  //     /*
      
  //     */
  //   }
  // }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // console.log(name, value)
    if(name !== RepeatedPasswordName)
      setRegisterData((prevState) => ({
        ...prevState,
        [name]: value
      }));
    else
      setRepeatedPassword(value);
  };
  
  return (
    <>
    <span className="h1">STEP 4</span>
      <div className="d-flex align-items-center justify-content-center vh-100">
      <Container className="d-flex justify-content-center row col-lg-4 col-sm-6 col-8">
        <span className='text-white text-center h2 mb-3'> Enter login and password</span>
        <form onSubmit={handleSubmit} className="row d-flex justify-content-center">
          <Col className="d-flex flex-column">
            <InputField name="login" type="text" value={registerData.login} onChange={handleInputChange} placeholder="login"/>
            <InputField name="password" type="password" value={registerData.password} onChange={handleInputChange} placeholder="password"/>
            <InputField name={RepeatedPasswordName} type="password" value={repeatedPassword} onChange={handleInputChange} placeholder="repeat password"/>
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
