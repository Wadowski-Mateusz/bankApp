import { useState, useEffect } from 'react';
import { Container, Row, Col, Form } from "react-bootstrap";
import { States } from "./States";
import Announcement from "./Announcement";
import { AnnouncementDTO, AnnouncementDeleteDTO } from "../../DTOs/AnnouncementDTO";
import axios from 'axios';

import * as endpoints from "../../endpoints/endpoints"


interface Props {
  setStateInPanel: (state: number) => void,
}

export default function DeleteAnnouncement ({ setStateInPanel }: Props) {
  const [windowHeight, setWindowHeight] = useState(window.innerHeight);
  const [announcements, setAnnouncements] = useState<AnnouncementDTO[]>([]);
  const userId = localStorage.getItem("userId") || "";
  const token = localStorage.getItem("jwt") || "";

  const fetchAnnouncementsList = async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${token}` }
      };
      const response = await axios.get(
        endpoints.GET_AVAILABLE_ANNOUNCEMENTS,
        config
        );
      const data = response.data;
      setAnnouncements(data);
      console.log("response: ", data)
    } catch (error) {
      console.error('fetch announcements error:', error);
    }
  };

  async function deleteAnnouncement(announcementId: string) {
    const annDel: AnnouncementDeleteDTO = {
      announcementId: announcementId,
      deletingUserId: userId,
    }
    const config = {
      headers: { Authorization: `Bearer ${token}` }
    };
    const response = await axios.post(
      endpoints.DELETE_ANNOUNCEMENT,
      annDel,
      config
    );
    if (response.status === 200) {
      console.log("Success")
      fetchAnnouncementsList();
    } else {
      console.log("Failure")
    }
    console.log("DELETE POST ASDASDASDASD", response.data)
  }

  // load announcements 
  useEffect(() => {
    if(token && userId)
      fetchAnnouncementsList();
  }, [token, userId]);


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

        {announcements.map((announcement) => (
                <Announcement key={announcement.id} announcementDTO={announcement} deleteAnnouncement={deleteAnnouncement} /> 
              ))}

      </Container>
    <Row className="gap-1 mt-4">
      <Col type="button" onClick={() => handleClick(0)} className="btn btn-primary rounded-4">
        <span>Back</span>
      </Col>
    </Row>

  </Container>
  );
}
