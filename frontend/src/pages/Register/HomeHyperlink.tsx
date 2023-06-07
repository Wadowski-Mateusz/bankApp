import React, { ChangeEvent, useContext, useLayoutEffect } from "react";
import { Link } from "react-router-dom";
import { RegisterDataContext } from "./Register";
import { ROLE_EMPLOYEE } from "../../endpoints/roles";

export default function HomeHyperlink() {
  const { role } = useContext(RegisterDataContext);
  return (
    <>
    {
    role !== ROLE_EMPLOYEE
    && 
    <Link to="/" className="text-white row d-flex justify-content-center mt-2">
      Back to homepage 
    </Link>
    }

    {
    role === ROLE_EMPLOYEE
    && 
    <Link to="/panel" className="text-white row d-flex justify-content-center mt-2">
      Back to panel 
    </Link>
    }
    </>
  );
}
