import { useNavigate } from "react-router-dom";
import Alert from "react-bootstrap/Alert"
import LoginForm from "./LoginForm";
import { useState } from "react";


function Login() {
  const navigate = useNavigate();
  const [auth, setAuth] = useState(false);  
  const [submited, setSubmited] = useState(false);  

  function verify(login: string, password: string) {
    if(login === "admin" && password === "admin" ) {
      navigate("/account"); 
    }
    if(login === "" && password === "" ) {
      navigate("/account"); 
    }
    else
      setAuth(false);
    setSubmited(true);
  }
  

  return (
    <>
    {submited && !auth && <Alert  className="alert-danger text-center">My Alert</Alert>}
    {submited && auth && <Alert className="alert-success text-center">My Alert</Alert>}
    <div className="d-flex align-items-center vh-100">
      <div className="container col-6">
        <div className="row text-center">
          <h1>[logo] Marsupium</h1>
        </div>
        <div className="row justify-content-center">
          <LoginForm verify={verify}/>
        </div>
        <div className="row mt-2 text-center">
          <a href="#" className="text-light">
            {" "}
            Don't have an account? Sign up!
          </a>
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
