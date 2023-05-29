import { useEffect, useState } from "react";
import MyNavbar from "../Nav/MyNavbar";
import Footer from "../Nav/Footer";
import Loan from "./Loan";


export default function Loans() {


  const loan1 = {
    id: "00000000-0000-0000-0000-000000000000",
    name: "loan 1",
    dateFrom: "2023-01-01",
    dateTo: "2023-12-31",
    interest: 5,
    loanDue: 100000,
  };

  const loan2 = {
    id: "00000000-1111-1111-1111-000000000000",
    name: "loan 2",
    dateFrom: "2023-01-01",
    dateTo: "2023-12-31",
    interest: 5,
    loanDue: 100000,
  };

  const [btnRequestContent, setBtnRequestContent] = useState("Request for a loan");
  const [loans, setLoans] = useState([loan1, loan2]);
  // const [loans, setLoans] = useState([loan1, loan2, loan2, loan2, loan2, loan2, loan2, loan2, loan2, loan2]);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    id:"",
    name: "",
    amount: "",
    months: "",
    interest: "0"
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    const MAX_MONTHS = 600
    const MAX_AMOUNT = 10_000_000
    let valid = true;
    let endValue = value
    
    switch (name) {
      case 'name':
        if(value.length > 32)
        valid = false
        break;
      case 'amount':
        if(value === "") break;
        if (Number.isNaN(Number(value)) || Number(value) < 0) {
            valid = false;
            break;
        }
        if (value.includes('.')) {
          valid = (value.split('.')[1].length <= 2)
        }
        if(Number(value) > MAX_AMOUNT)
          endValue = MAX_AMOUNT.toString()
        break;
      case 'months':
        if(value === "") break;
        if (!Number.isInteger(Number(value)) || value.includes('.')) {
          valid = false;
          break;
        }
        valid = (0 < Number(value)) // 50 years
        if(Number(value) > MAX_MONTHS) {
          endValue = MAX_MONTHS.toString()
        }
        
        break;
      }

    if(valid) {
      setFormData((prevFormData) => ({  
        ...prevFormData,
        [name]: endValue
      }));    
    }
  };

  useEffect(() => {
    let interest = ""
    if (formData.months) {
      interest = (
        1.5 +
        Number(formData.months) / 10.0 +
        Math.floor(Number(formData.months) / 6)
        ).toFixed(2).toString();
    }
      setFormData((prevFormData) => ({
        ...prevFormData,
        interest: interest
      }));
  }, [formData.months]);
  


  
  const handleAddLoan = () => {
    if(showForm) 
      setBtnRequestContent("Request for a loan")
    else 
      setBtnRequestContent("Hide")
    setShowForm(!showForm);
  };

  const handleRequestLoan = () => {
    
    const newLoan: Props = {
      id: "uuid here",
      name: formData.name,
      dateFrom: "today",
      dateTo: "today",
      interest: formData.interest,
      loanDue: (Number(formData.amount) * (1 + Number(formData.interest) / 100)).toFixed(2).toString(),
    };

    setLoans([...loans, newLoan]);
    setShowForm(false);

    formData.id=""
    formData.name=""
    formData.amount=""
    formData.months=""
    formData.interest="0"
    
    setBtnRequestContent("Request for a loan");
  };

  return (
    <>
      <MyNavbar />
        <div className="d-flex align-items-center pt-5 pb-5 h-100">
          <div className="container rounded d-flex col-10 flex-column background-color-container p-5 pt-4 rounded-5 border border-white border-1 h-100">
            <button onClick={handleAddLoan}
              type="button"
              className="btn btn-primary btn-lg align-self-end p-3 ps-4 pe-4 rounded-4"
            >
              {btnRequestContent}
            </button>

            { showForm && (<div id="request-container" className="d-flex flex-column align-items-center">
              <form className="bg-info rounded-4 p-3 d-flex flex-column jsutify-content-center">
                  <div className="d-flex justify-content-between">
                    Name: 
                    <input className="rounded-2 ms-5" name="name" type="text" value={formData.name} onChange={handleInputChange} placeholder="Loan for live" />
                  </div>
                  <div className="d-flex justify-content-between">
                    Amount: 
                    <input className="rounded-2" name="amount" type="text" value={formData.amount} onChange={handleInputChange} placeholder="0"/> 
                  </div>
                  <div className="d-flex justify-content-between">
                    Months:
                    <input className="rounded-2" name="months" type="text" value={formData.months} onChange={handleInputChange} placeholder="0"/> 
                  </div> 
                  <div className="d-flex justify-content-between">
                    Interest: <span>{formData.interest}%</span>
                  </div>
                  <button type="button" onClick={handleRequestLoan} className="btn btn-primary">Request for loan</button>
              </form>
            </div>) }

            <div
              id="loans-container"
              className="container mt-3 text-light h-100 overflow-y-auto"
            >
              {loans.length === 0 && (
                <div className="row h3 d-flex justify-content-center">
                  You don't have any loan.
                </div>
              )}
              <div>{loans.map(loan => (
                <Loan key={loan.id} {...loan} /> 
              ))}</div>
            </div>
          </div>
        </div>
        <Footer />
    </>
  );
}

