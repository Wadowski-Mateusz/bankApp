import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { Container, Row } from "react-bootstrap";
import * as endpoints from "../../endpoints/endpoints";
import axios, { HttpStatusCode } from "axios";

export default function NotFound() {
  return (
    <div className="d-flex align-items-center vh-100">
      <div className="container text-light col-lg-4 col-md-6 col-8 h-75 d-flex align-items-center">
        <Container
          className="
        background-color-container 
        py-3 px-4 rounded-5 
        border border-white border-1 
        d-flex flex-column justify-content-between 
        h-auto h1 text-center"
        >
          Page not found
          <Link to="/" className="h3 mt-3"> Back to homepage</Link>
        </Container>
      </div>
    </div>
  );
}
