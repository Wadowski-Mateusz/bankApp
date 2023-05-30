import { useState } from 'react';

import StepOne from "./StepOne";
import StepTwo from "./StepTwo";
import StepThree from "./StepThree";
import StepFour from "./StepFour";
import StepFive from "./StepFive";

export default function Register() {

  // indexed from 1
  const [stepNo, setStepNo] = useState(1);

  function move(moveTo: number) {
    setStepNo(moveTo)
  }


  return (
    <>
    {stepNo === 1 && <StepOne move={move} stepId={1}/>}
    {stepNo === 2 && <StepTwo move={move} stepId={2}/>}
    {stepNo === 3 && <StepThree move={move} stepId={3}/>}
    {stepNo === 4 && <StepFour move={move} stepId={4}/>}
    {stepNo === 5 && <StepFive move={move} stepId={5}/>}
    </>
  );
}
