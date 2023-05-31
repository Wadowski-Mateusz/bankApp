import { useState, useEffect } from 'react';
import { Container, Row, Col, Form } from "react-bootstrap";
import { States } from "./States";
import Announcement from "./Announcement";

interface Props {
  setStateInPanel: (state: number) => void,
}

export default function DeleteAnnouncement ({ setStateInPanel }: Props) {
  const [windowHeight, setWindowHeight] = useState(window.innerHeight);

  useEffect(() => {
    const handleWindowResize = () => {
      setWindowHeight(window.innerHeight);
    };

    window.addEventListener('resize', handleWindowResize);

    return () => {
      window.removeEventListener('resize', handleWindowResize);
    };
  }, []);

  function handleClick(action: number) {
    console.log(action)
    if(action === 1) {
      // delete announcement
    }
    setStateInPanel(States.Default)
  }

  return (
    <Container className=" background-color-container py-3 px-4 rounded-5 border border-white border-1 d-flex flex-column justify-content-between h-auto">
      <Container id="announcement-container"  className="overflow-y-auto" style={{maxHeight: windowHeight * 0.65 }}>
        <Announcement />
        <Announcement />
        <Announcement />
        <Announcement />
        <Announcement />
        <Announcement />
        <Announcement />
        <Announcement />
      </Container>
    <Row className="gap-1 mt-4">
      <Col type="button" onClick={() => handleClick(0)} className="btn btn-primary rounded-4">
        <span>Back</span>
      </Col>
    </Row>

  </Container>
  );
}
