import { useState } from "react";
import MyNavbar from "./MyNavbar";

function Loans() {


const [loans, setLoans] = useState([]);

  return (
    <>
    <MyNavbar />
    <div className="d-flex align-items-center pt-5 pb-5 h-100">
      <div className="container rounded d-flex col-10 flex-column background-color-container p-5 pt-4 rounded-5 border border-white border-1 h-100">
        <button
          type="button"
          className="btn btn-primary btn-lg align-self-end p-3 ps-4 pe-4 rounded-4"
        >
          Request for loan
        </button>
        <div
          id="loans-container"
          className="container mt-3 text-light h-100 overflow-y-auto flex-row"
        >
          {loans.length === 0 && (
            <div className="row h3 d-flex justify-content-center">
              You don't have any loan.
            </div>
          )}
          <div className="row p-3 bg-primary rounded-4 m-2 d-flex justify-content-center flex-column">
            <span className="d-flex justify-content-around">
              <span>
                <span className="fw-bold">Name</span>:{" "}
                <span id="loan-name">Loan for live</span>
              </span>
              <span>
                <span className="fw-bold">From</span>:{" "}
                <span id="loan-from">2023-04-12</span>
              </span>
              <span>
                <span className="fw-bold">To</span>:{" "}
                <span id="loan-to">2024-04-12</span>
              </span>
              <span>
                <span className="fw-bold">Interets</span>:{" "}
                <span id="loan-interest">23%</span>
              </span>
              <span>
                <span className="fw-bold">To be repaid</span>:{" "}
                <span id="loan-due">100000</span>
              </span>
            </span>
          </div>
          <div className="row p-3 bg-primary rounded-4 m-2 d-flex justify-content-center flex-column">
            <span className="d-flex justify-content-around">
              <span>
                <span className="fw-bold">Name</span>:{" "}
                <span id="loan-name">Loan for live</span>
              </span>
              <span>
                <span className="fw-bold">From</span>:{" "}
                <span id="loan-from">2023-04-12</span>
              </span>
              <span>
                <span className="fw-bold">To</span>:{" "}
                <span id="loan-to">2024-04-12</span>
              </span>
              <span>
                <span className="fw-bold">Interets</span>:{" "}
                <span id="loan-interest">23%</span>
              </span>
              <span>
                <span className="fw-bold">To be repaid</span>:{" "}
                <span id="loan-due">100000</span>
              </span>
            </span>
          </div>
        </div>
      </div>
    </div>
    </>
    /* This will be shown after pressing request button
    <div id="request-container" style="display:none">
        <form>
            <div className="input-row">Name: <input type="text" placeholder="Loan for live"> </div>
            <div className="input-row">Amount: <input type="text"> </div>
            <div className="input-row">Time:  [TODO change to select]<input type="text"> </div> 
            <div className="input-row">Interest: 123% </div>
        </form>
        <button type="button" className="btn btn-primary">Request for loan</button>
    </div> */
    
  );
}

export default Loans;
