import React, { useRef, useState } from "react";
import { Container, Row, Col, Form } from "react-bootstrap";
import { AnnouncementDTO } from "../DTOs/AnnouncementDTO";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css"
import axios from 'axios';


import { States } from "./States";
import { ADD_ANNOUNCEMENT_ENDPOINT } from "../../endpoints/announcementsEndpoints"


interface Props {
  setStateInPanel: (state: number) => void;
}

export default function AddAnnouncement({ setStateInPanel }: Props) {
  const UserIdToDelete: string = "5151f90e-ce44-4784-bb73-26601cb2cbd9";


  const [announcementToAdd, setAnnouncementToAdd] = useState<AnnouncementDTO>({
    id: null,
    content: "",
    dateFrom: new Date(),
    dateTo: new Date(),
    authorId: UserIdToDelete,
  });


  async function handleClick(action: number) {
    console.log(action);
    if (action === 1) {
      try {
        console.log("CLICK:", announcementToAdd)
        const response = await axios.post(ADD_ANNOUNCEMENT_ENDPOINT, announcementToAdd);
        const data: AnnouncementDTO = response.data;
        console.log("RESPONSE:", data)

      } catch (error) {
        console.error('fetch error:', error);
      }
    }
    setStateInPanel(States.Default);
  }

  return (
    <>
      <Container className=" background-color-container py-3 px-4 rounded-5 border border-white border-1 d-flex flex-column justify-content-between h-auto">
        <Form
          id="announcement-form"
          className="mt-3 mb-1 d-flex flex-column align-items-center"
        >
          <Row className="gap-1 d-flex justify-content-between col-12">
            <Col className="text-white col-md-5 col-12">
              <label htmlFor="To" className="form-label ms-2">
                Visible from
              </label>
              <DatePicker 
              selected = {announcementToAdd.dateFrom}
                onChange={(date: Date) => setAnnouncementToAdd((prevState) => ({
                    ...prevState,
                    dateFrom: date
                }))}
                className="form-control"
              />
            </Col>

            <Col className="col-md-5 col-12">
            <label htmlFor="To" className="form-label ms-2"> Visible to </label>
            <DatePicker 
              selected = {announcementToAdd.dateTo}
                onChange={(date: Date) => setAnnouncementToAdd((prevState) => ({
                    ...prevState,
                    dateTo: date
                }))}
                className="form-control"
              />
            </Col>
          </Row>

          <Row className="text-dark col-12 mt-4">
            <Col>
              <textarea
                value={announcementToAdd.content}
                onChange={(e) =>
                  setAnnouncementToAdd((prevState) => ({
                    ...prevState,
                    content: e.target.value,
                  }))
                }
                id="content"
                className="form-control"
                placeholder="Announcement content"
                rows={5}
              />
              <label htmlFor="flaotingTextarea2" className="form-label ms-2">
                Content
              </label>
            </Col>
          </Row>

          <Row id="navigation" className="gap-1 col-12">
            <Col
              type="button"
              onClick={() => handleClick(0)}
              className="btn btn-primary rounded-4"
            >
              <span>Cancel</span>
            </Col>
            <Col
              type="button"
              onClick={() => handleClick(1)}
              className="btn btn-primary rounded-4"
            >
              <span>Add</span>
            </Col>
          </Row>
        </Form>
      </Container>
    </>
  );
}


{/* <input
value={announcementToAdd.dateFrom.toISOString()}
onChange={(e) =>
  setAnnouncementToAdd((prevState) => ({
    ...prevState,
    dateFrom: new Date(e.target.value),
  }))
}
type="date"
className="form-control"
placeholder="announcement will be visible from"
/> */}