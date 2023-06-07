import React, { ChangeEvent, useContext, useLayoutEffect } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useNavigate, Link } from "react-router-dom";

import { RegisterDataContext } from "./Register";
import HomeHyperlink from "./HomeHyperlink";
import InputField from "./InputField";

import DatePicker from "react-datepicker";
import { ROLE_EMPLOYEE } from "../../endpoints/roles";

interface Props {
  move: (moveTo: number) => void;
  stepId: number;
}

export default function StepOne({ move, stepId }: Props) {
  const { registerData, setRegisterData, idScan, setIdScan, role } = useContext(RegisterDataContext);
  const navigate = useNavigate();

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    // validation
    const btnName = (e.nativeEvent.submitter as HTMLButtonElement).name;
    if (btnName === "next") {
      move(stepId + 1);
    } else {
      // move(stepId - 1);
      if(role===ROLE_EMPLOYEE)
        navigate("/panel")
      else
        navigate("/");
    }
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // console.log(name, value)
    setRegisterData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleFileSelect = (files: File[]) => {
    setIdScan(files[0]);
  };

  return (
    <>
      <div className="d-flex align-items-center justify-content-center vh-100">
        <Container className="d-flex justify-content-center row col-8">
          <span className="text-white text-center h2 mb-3">
            {role!==ROLE_EMPLOYEE && <span>Enter your data</span>}
            {role===ROLE_EMPLOYEE && <span>Enter client data</span>}
            
          </span>
          <form
            onSubmit={handleSubmit}
            className="row d-flex justify-content-center"
          >
            <Col className="d-flex flex-column gap">
              <InputField
                name="firstName"
                type="text"
                value={registerData.firstName}
                onChange={handleInputChange}
                placeholder="First Name"
              />
              <InputField
                name="lastName"
                type="text"
                value={registerData.lastName}
                onChange={handleInputChange}
                placeholder="Last Name"
              />
              {/* <InputField type="date" name="birthday" value={registerData.birthday} onChange={handleInputChange} placeholder="Select birthday"/> */}
              <DatePicker
                selected={registerData.birthday}
                onChange={(date: Date) =>
                  setRegisterData((prevState) => ({
                    ...prevState,
                    birthday: date,
                  }))
                }
                className="rounded-2 m-1 col-12 form-control"
                placeholderText="Select birthday"
              />

              {/* <input type="text" placeholder="Birthday" className="rounded-2 m-1" /> */}
            </Col>
            <Col className="d-flex flex-column">
              <InputField
                name="email"
                type="email"
                value={registerData.email}
                onChange={handleInputChange}
                placeholder="email@example.com"
              />

              {/* <InputField name="idScan" type="file" value={registerData.idScan} onChange={handleInputChange} placeholder="ID Scan"/> */}

              <InputField
                name="idNumber"
                type="text"
                value={registerData.idNumber}
                onChange={handleInputChange}
                placeholder="ID number"
              />

              <input
                type="file"
                id="file"
                onChange={(e) => handleFileSelect(e.target.files || [])}
                accept="image/*"
                className="form-control rounded-2 m-1"
                />
              <label htmlFor="file" className="text-white">Select Scan ID</label>

            </Col>

            <Row className="d-flex justify-content-evenly">
              <button
                name="back"
                className="btn btn-primary col-xxl-4 col-sm-5 col-12 mt-3"
              >
                Back
              </button>
              <button
                name="next"
                className="btn btn-primary col-xxl-4 col-sm-5 col-12 mt-3"
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
