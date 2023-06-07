import axios from "axios";

import Login from "./pages/Login/Login";
import Loans from "./pages/Loan/Loans";
import Account from "./pages/Account/Account";
import Settings from "./pages/Settings/Settings";
import PanelEmp from "./pages/EmployeePanel/PanelEmp";
import Register from "./pages/Register/Register";
import NotFound from "./pages/NotFound/NotFound";
import Test from "./Test";
import "../css/main.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ROLE_ADMIN, ROLE_CLIENT, ROLE_EMPLOYEE } from "./endpoints/roles";

axios.defaults.baseURL = "http://localhost:8081";

export default function App() {
  const role = localStorage.getItem("role");
  return (
    <>
      {/* {role} */}
      <BrowserRouter>
        <Routes>
          {/* all */}
          <Route path="" element={<Login />}></Route>
          <Route path="/" element={<Login />}></Route>
          <Route path="/login" element={<Login />}></Route>

          {/* no_role + employee */}
          {/* {(!role || role === ROLE_EMPLOYEE) && ( */}
              <Route path="/register" element={<Register />}></Route>
          {/* )} */}

          {/* client */}
          {/* {role === ROLE_CLIENT && ( */}
            <>
              <Route path="/account" element={<Account />}></Route>
              <Route path="/loans" element={<Loans />}></Route>
              <Route path="/settings" element={<Settings />}></Route>
            </>
          {/* )} */}

          {/* employee */}
          {/* {role === ROLE_EMPLOYEE && ( */}
            <>
              <Route path="/panel" element={<PanelEmp />}></Route>
            </>
          {/* )} */}
          {/* admin */}
          {/* {role === ROLE_ADMIN && ( */}
            <Route path="/test" element={<Test />}></Route>
          {/* )} */}

          <Route path="*" element={<NotFound />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}
