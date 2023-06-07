import axios from 'axios';

import Login from "./pages/Login/Login";
import Loans from "./pages/Loan/Loans";
import Account from "./pages/Account/Account";
import Settings from "./pages/Settings/Settings";
import PanelEmp from "./pages/EmployeePanel/PanelEmp";
import Register from "./pages/Register/Register";
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
