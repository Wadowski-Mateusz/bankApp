import { useState } from 'react';

import StepOne from "./StepOne";
import StepTwo from "./StepTwo";
import StepThree from "./StepThree";
import StepFour from "./StepFour";
import StepFive from "./StepFive";

export default function Register() {

  // indexed from 1
  const [stepNo, setstepNo] = useState(false);  

  return (
    <>
    <StepOne/>
    <StepTwo/>
    <StepThree/>
    <StepFour/>
    <StepFive/>
    </>
  );
}
