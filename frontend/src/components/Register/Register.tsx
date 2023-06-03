  import React, { useState } from 'react';

  import StepOne from "./StepOne";
  import StepTwo from "./StepTwo";
  import StepThree from "./StepThree";
  import StepFour from "./StepFour";
  import StepFive from "./StepFive";
  import { RegisterDTO } from '../DTOs/RegisterDTO';

  const initialRegisterData: RegisterDTO = {
    firstName: "",
    lastName: "",
    birthday: "",
    email: "",
    // idURI: "",  
    // idScan: null,
    idNumber: "",
    country: "",
    sector: "",
    city: "",
    street: "",
    number: "",
    zip: "",
    login: "",
    password: "",
    isVerified: false,
  };

  // export const RegisterDataContext = React.createContext<{
  //   registerData: RegisterDTO;
  //   setRegisterData: React.Dispatch<React.SetStateAction<RegisterDTO>>;
  // }>({
  //   registerData: initialRegisterData,
  //   setRegisterData: () => {},
  // });
  
  export const RegisterDataContext = React.createContext<{
    registerData: RegisterDTO;
    setRegisterData: React.Dispatch<React.SetStateAction<RegisterDTO>>;
    idScan: File;
    setIdScan: React.Dispatch<React.SetStateAction<File>>;
  }>({
    registerData: initialRegisterData,
    setRegisterData: () => {},
    idScan: "",
    setIdScan: () => {}
  });
  

  export default function Register() {
    const [registerData, setRegisterData] = useState<RegisterDTO>(initialRegisterData);
    const [idScan, setIdScan] = useState<File>("");

    // indexed from 1
    const [stepNo, setStepNo] = useState(1);

    function move(moveTo: number) {
      console.log("current", stepNo, "after changes", moveTo)
      setStepNo(moveTo)
    }


    return (
      <RegisterDataContext.Provider value={{ registerData, setRegisterData, idScan, setIdScan }}>
        <button onClick={(e)=> console.log(registerData)}>clicky click</button>
        <button onClick={(e)=> console.log(idScan)}>ID SCAN</button>
        {stepNo === 1 && <StepOne move={move} stepId={1}/>}
        {stepNo === 2 && <StepTwo move={move} stepId={2}/>}
        {stepNo === 3 && <StepThree move={move} stepId={3}/>}
        {stepNo === 4 && <StepFour move={move} stepId={4}/>}
        {stepNo === 5 && <StepFive/>}
      </RegisterDataContext.Provider>
    );
  }
