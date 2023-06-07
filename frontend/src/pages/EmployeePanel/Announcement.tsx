import { Container, Row, Col, Form, Button } from "react-bootstrap";

import React, { useState } from "react";
import { AnnouncementDTO } from "../../DTOs/AnnouncementDTO";

interface Props {
  announcementDTO: AnnouncementDTO,
  deleteAnnouncement: (announcementId: string) => void
}

export default function Announcement( {announcementDTO, deleteAnnouncement} : Props ) {


  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // validation
    deleteAnnouncement(announcementDTO.id!!)
  }

  return(
    <Container className="d-flex flex-column justify-content-center align-content-center border rounded-4 my-3">
    <Form onSubmit={handleSubmit} className="gap-1 row d-flex flex-row justify-content-center align-items-center">
      <Col className="col-10">
        <Row>
          <Col>From: {announcementDTO.dateFrom} </Col>
          <Col>To: {announcementDTO.dateTo}</Col>
        </Row>
        <Row className="border border-1 border-info rounded-4 mb-1">
          <Col className="overflow-y-auto" style={{maxHeight: 150 }}>
              {announcementDTO.content}
          </Col>
        </Row>
      </Col>

      <Button type="submit" className="col-1 text-danger fs-4 text-center">
        X
      </Button>
    </Form>
  </Container>
  )
}