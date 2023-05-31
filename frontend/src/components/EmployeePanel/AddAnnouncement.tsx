import React, { useState } from "react";
import { Container, Row, Col, Form } from "react-bootstrap";
import { States } from "./States";

interface Props {
  setStateInPanel: (state: number) => void,
}

export default function AddAnnouncement ({ setStateInPanel }: Props) {

  function handleClick(action: number) {
    console.log(action)
    if(action === 1) {
      // add announcement
    }
    setStateInPanel(States.Default)
  }

  return (
    <>
    <Container className=" background-color-container py-3 px-4 rounded-5 border border-white border-1 d-flex flex-column justify-content-between h-auto">

      <Form id="announcement-form" className="mt-3 mb-1 d-flex flex-column align-items-center">
        <Row className="gap-1 d-flex justify-content-between col-12"> 
          <Col className="form-floating text-dark col-md-5 col-12">
              <input type="date" id="fromDate" className="form-control" placeholder="announcement will be visible from" />
              <label htmlFor="To" className="form-label ms-2">Visible from</label>
          </Col>
          <Col className="form-floating text-dark col-md-5 col-12">
              <input type="date" id="toDate" className="form-control" placeholder="announcement will be visible to" />
              <label htmlFor="To" className="form-label ms-2">Visible to</label>
          </Col>  
        </Row>
        
        <Row className="text-dark col-12 mt-4">
          <Col className="">
            <textarea id="content" className="form-control" placeholder="Announcement content" rows={5}/>
            <label htmlFor="flaotingTextarea2" className="form-label ms-2">Content</label>
          </Col>
        </Row>

        <Row id="navigation" className="gap-1 col-12">
          <Col type="button" onClick={() => handleClick(0)} className="btn btn-primary rounded-4">
            <span>Cancel</span>
          </Col>
          <Col type="button" onClick={() => handleClick(1)} className="btn btn-primary rounded-4">
            <span>Add</span>
          </Col>
        </Row>
      </Form>

    </Container>
   </>
  );
}
