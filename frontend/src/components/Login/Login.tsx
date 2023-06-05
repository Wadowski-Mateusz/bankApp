import { useNavigate, Link } from "react-router-dom";
import Alert from "react-bootstrap/Alert";
import React, { useState, useEffect } from "react";
import axios from 'axios';

import LoginForm from "./LoginForm";
import Announcement from "./Announcement";
import MyNavbar from "../Nav/MyNavbar";

import * as Endpoint from "../../endpoints/endpoints";
import {RANDOM_ANNOUNCEMENT_ENDPOINT} from "../../endpoints/announcementsEndpoints";
import {AnnouncementDTO} from "../DTOs/AnnouncementDTO";

interface UserDTO {
  id: string;
  fullName: string;
}

function Login() {
  const navigate = useNavigate();
  const [submited, setSubmited] = useState(false);  
  const [announcement, setAnnouncement] = useState<AnnouncementDTO>();  



  useEffect(() => {
    const fetchAnnouncement = async () => {
      try {
        const response = await axios.get(`${RANDOM_ANNOUNCEMENT_ENDPOINT}`);
        const data: AnnouncementDTO = response.data;
        setAnnouncement(data);
      } catch (error) {
        console.error('fetch error:', error);
      }
    };
    fetchAnnouncement();
  }, []);



  async function verify(login: string, password: string) {
    if(login === "" && password === "" ) {
      navigate("/account"); 
    }

    try {
      const response = await axios.post<UserDTO>(Endpoint.LOGIN_ENDPOINT, {
        login: login,
        password: password,
      });
  
      console.log(response)
      console.log(response.data)
      
      navigate("/account");
    } catch (error) {
      setSubmited(true);
      console.error('Login failed:', error);
    }
  }


  return (
    <>
    <MyNavbar />
    {submited && <Alert  className="alert-danger text-center">Wrong login or password</Alert>}
    <div className="d-flex align-items-center vh-100">
      <div className="container col-6">
        <div className="row text-center">
          <h1>[logo] Marsupium</h1>
        </div>
        <div className="row justify-content-center">
          <LoginForm verify={verify}/>
        </div>
        <div className="row mt-2 text-center">
          <Link to="register" className="text-light">
            Don't have an account? Sign up!
          </Link>
        </div>

        {/* <Announcement content={announcement!!.content}/> */}
        <Announcement content={announcement?.content}/>


      </div>
    </div>
    </>
  );
}

export default Login;
