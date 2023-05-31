import { useNavigate, Link } from "react-router-dom";
import Alert from "react-bootstrap/Alert";
import React, { useState } from "react";
import axios from 'axios';


import LoginForm from "./LoginForm";
import MyNavbar from "../nav/MyNavbar";
import {LOGIN_ENDPOINT} from "../../endpoints/userEndpoints";

interface UserDTO {
  id: string;
  fullName: string;
}

function Login() {
  const navigate = useNavigate();
  const [submited, setSubmited] = useState(false);  


  async function verify(login: string, password: string) {

    if(login === "" && password === "" ) {
      navigate("/account"); 
    }

    try {
      // const response = await axios.post<UserDTO>('http://localhost:8081/user/login', {
      const response = await axios.post<UserDTO>(LOGIN_ENDPOINT, {
        login: login,
        password: password,
      });
  
      const { id, fullName } = response.data;
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

        <div className="row justify-content-center mt-2">
          <div
            id="announcment"
            className="text-center background-color-container col-lg-5 col-md-8 p-3 rounded-3 text-light"
          >
            Here will be an announcment.
          </div>
        </div>
      </div>
    </div>
    </>
  );
}

export default Login;
