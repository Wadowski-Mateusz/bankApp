import axios from 'axios';

import Login from "./components/Login/Login";
import Loans from "./components/Loan/Loans";
import Account from "./components/Account/Account";
import Settings from "./components/Settings/Settings";
import PanelEmp from "./components/EmployeePanel/PanelEmp";
import Register from "./components/Register/Register";
import Test from "./Test";
import "../css/main.css"

import { BrowserRouter, Route, Routes } from "react-router-dom";

axios.defaults.baseURL = 'http://localhost:8081';

export default function App() {
    return (
    <>
      <BrowserRouter>
      <Routes>
        <Route path="" element={<Login />}></Route>
        <Route path="/" element={<Login />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/account" element={<Account />}></Route>
        <Route path="/panel" element={<PanelEmp />}></Route>
        <Route path="/loans" element={<Loans />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/settings" element={<Settings />}></Route>
        <Route path="/test" element={<Test />}></Route>
      </Routes>
      </BrowserRouter>
    </>
    );
}
