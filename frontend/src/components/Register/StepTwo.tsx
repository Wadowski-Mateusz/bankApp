import { Row, Col, Container } from "react-bootstrap";
import React, { useContext } from "react";


import { RegisterDataContext } from "./Register";
import HomeHyperlink from "./HomeHyperlink";
import InputField from "./InputField";

interface Props {
  move: (moveTo: number) => void;
  stepId: number;
}

export default function StepTwo({ move, stepId }: Props) {
  const { registerData, setRegisterData } = useContext(RegisterDataContext);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    // validation
    const btnName = (e.nativeEvent.submitter as HTMLButtonElement).name;
    if (btnName === "next") {
      move(stepId + 1);
    } else {
      move(stepId - 1);
    }
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // console.log(name, value)
    setRegisterData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  
  return (
    <>
      <span className="h1">STEP 2</span>
      <div className="d-flex align-items-center justify-content-center vh-100">
        <Container className="d-flex justify-content-center row col-8">
          <span className="text-white text-center h2 mb-3">
            Please enter your Address
          </span>
          <form
            onSubmit={handleSubmit}
            className="row d-flex justify-content-center"
          >
            <Col className="d-flex flex-column">
              <InputField name="country" type="text" value={registerData.country} onChange={handleInputChange} placeholder="Country"/>
              <InputField name="sector" type="text" value={registerData.sector} onChange={handleInputChange} placeholder="Sector"/>
              <InputField name="city" type="text" value={registerData.city} onChange={handleInputChange} placeholder="City"/>
            </Col>

            <Col className="d-flex flex-column">
              <InputField name="street" type="text" value={registerData.street} onChange={handleInputChange} placeholder="Street"/>
              <InputField name="number" type="text" value={registerData.number} onChange={handleInputChange} placeholder="Number"/>
              <InputField name="zip" type="text" value={registerData.zip} onChange={handleInputChange} placeholder="Zip code"/>
            </Col>
            <Row className="d-flex justify-content-evenly">
              <button
                name="back"
                className="btn btn-primary col-xxl-4 col-5 mt-3"
              >
                Back
              </button>
              <button
                name="next"
                className="btn btn-primary col-xxl-4 col-5 mt-3"
              >
                Next
              </button>
            </Row>
          </form>
          <HomeHyperlink />
        </Container>
      </div>
    </>
  );
}
