import { useNavigate, Link } from "react-router-dom";
import Alert from "react-bootstrap/Alert";
import React, { useState, useEffect } from "react";
import axios, { HttpStatusCode } from 'axios';
import jwt_decode from 'jwt-decode';

import LoginForm from "./LoginForm";
import Announcement from "./Announcement";
import MyNavbar from "../Nav/MyNavbar";

import * as Endpoint from "../../endpoints/endpoints";
import * as roles from "../../endpoints/roles";

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


  // Fetching announcement to display
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
    // bypass
    // if(login === "" && password === "" ) {
    //   navigate("/account"); 
    // }

    try {
      const response = await axios.post<{token: string}>(Endpoint.LOGIN_ENDPOINT, {
        login: login,
        password: password,
      });
      console.log("response:", response);
      if(response.status === HttpStatusCode.Ok) {
        const token = response.data.token;
        const decodedToken = jwt_decode(token) as { [key: string]: any };
        const userId = decodedToken.userId;
        const fullName = decodedToken.fullName;
        const role = decodedToken.role;

        console.log(userId)
        console.log(fullName)
        console.log(role)


        localStorage.setItem('jwt', token);
        localStorage.setItem('userId', userId);
        localStorage.setItem('fullName', fullName);
        localStorage.setItem('role', role);
        if (role == roles.ROLE_CLIENT)
          navigate("/account");
        else if (role == roles.ROLE_EMPLOYEE)
          navigate("/panel");
        else
          console.log("Unknown role or admin")
      }
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

        {
          announcement?.content 
          &&
          <Announcement content={announcement?.content}/>
        }


      </div>
    </div>
    </>
  );
}

export default Login;
