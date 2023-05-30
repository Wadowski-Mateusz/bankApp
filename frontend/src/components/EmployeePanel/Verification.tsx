import { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";

import Address from "../../classes/Address"

interface UserVerificationDTO {
  name: string,
  email: string,
  birthday: string,
  address: Address,
  idNumber: string,
  idURI: string,
}

interface Props {
  setStateInPanel: (state: number) => void,

}

enum Action{
  Panel,
  Postpone,
  Verify,
}

export default function Verification ({ setStateInPanel }: Props) {
  const [inputValue, setInputValue] = useState(0);

  function handleClick(action: number) {
    console.log(action)
    switch(action) {
      case Action.Panel:
        setStateInPanel(0)
        break;
      case Action.Postpone:
        // postpone verification
        // fetch new user
        break;
      case Action.Verify:
        // verify in the database
        // fetch new user
        break;
    }
  }

  return (
    <>
      <Container
        id="panel-container"
        className="
        background-color-container 
        py-3 px-4 rounded-5 
        border border-white border-1 
        d-flex flex-column justify-content-between 
        h-auto"
        >
        {/* Top 3 rows */}
        <Row className="gap-3">
          <Col>
          <Row className="bg-primary rounded-3 my-3 p-1"><span>Name: John Carsa</span></Row>
          <Row className="bg-primary rounded-3 my-3 p-1"><span>email:<br/> email.email@example.com</span></Row>
          <Row className="bg-primary rounded-3 my-3 p-1"><span>Birthday: 30-03-1985</span></Row>
          </Col>

          <Col>
          <Row className="bg-primary rounded-4 my-3 p-1">
            <span className="text-center">
              Poland
            </span>
            <span className="text-center">
              Lesser Poland
            </span>
            <span className="text-center">
              Cracow 00-000
            </span>
            <span className="text-center">
              Szlak 999
            </span>
          </Row>
          <Row className="bg-primary rounded-3 my-3 p-1"><span>ID number: xyzabc</span></Row>
          </Col>
        </Row>

        {/* id pic */}
        <Row className="h1">ID PIC</Row>

        {/* navigation */}
        <Row className="gap-1 mt-4">
          <Col type="button" onClick={() => handleClick(Action.Panel)} className="btn btn-primary rounded-4">
            <span>Panel</span>
          </Col>
          <Col type="button" onClick={() => handleClick(Action.Postpone)} className="btn btn-primary rounded-4">
            <span>Postpone</span>
          </Col>
          <Col type="button" onClick={() => handleClick(Action.Verify)} className="btn btn-primary rounded-4">
            <span>Verify</span>
          </Col>
        </Row>

      </Container>
    </>
  );
}
