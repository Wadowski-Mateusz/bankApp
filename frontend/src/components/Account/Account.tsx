import {
  FormFloating,
  Row,
  Col,
  Container,
  Button,
  Form,
  FormControl,
  FormLabel,
} from "react-bootstrap";

import Table from "react-bootstrap/Table";
import React, { useState, useEffect } from "react";
import axios, { HttpStatusCode, AxiosRequestConfig } from "axios";
import DatePicker from "react-datepicker";

import MyNavbar from "../Nav/MyNavbar";
import TransactionView from "./TransactionView";
import { TransactionViewDTO, TransactionAddDTO } from "../DTOs/TransactionDTOs";
import { AccountDTO } from "../DTOs/AccountDTO";
import * as endpoints from "../../endpoints/endpoints";


export default function Account() {
  const userId = localStorage.getItem("userId") || "";
  const username = localStorage.getItem("fullName") || "";
  const [account, setAccount] = useState<AccountDTO>();
  const [token, setToken] = useState<String>(localStorage.getItem("jwt") || "");
  

  const NametoDelete = "1111-1111-1111";

  const [valueView, setValueView] = useState(""); // what user see, it is use to display dot in the number
  const [transactionViews, setTransactionViews] = useState<TransactionViewDTO[]>([]);
  const [transactionAdd, setTransactionAdd] = useState<TransactionAddDTO>({
    id: null,
    receiverAccountNumber: "",
    senderAccountNumber: NametoDelete,
    title: "",
    amount: 0,
    timestamp: new Date(),
  });

  function handleAmountChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { value } = e.target;
    let valid = true;

    if (value !== "") {
      if (Number.isNaN(Number(value)) || Number(value) < 0) {
        valid = false;
      } else if (value.includes(".")) {
        const splited = value.split(".");
        valid = value.split(".")[1].length <= 2;
      }
    }

    if (value.length > 1) {
      if (value.includes(".")) {
        if (value[0] === "0" && value[1] !== ".") valid = false;
      } else if (value[0] === "0") valid = false;
    }

    if (valid) {
      setValueView(value);
      if (value[value.length - 1] !== ".")
        setTransactionAdd((prevFormData) => ({
          ...prevFormData,
          amount: Number(value),
        }));
    }
  }

 
  const fetchTransactionList = async () => {
    try {
      const response = await axios.get(
        `${endpoints.GET_ALL_TRANSFERS_OF_USER}${userId}`
      );
      const data = response.data;
      setTransactionViews(data);
      console.log(data);
    } catch (error) {
      // console.error("fetch transactions error:", error);
    }
  };

  async function fetchAccountData() {
    if(token) {
      console.log("fetching account")
      try {
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        console.log("before fetch: ", config)
        console.log("before fetch: ", token)
        const response = await axios.get(
          `${endpoints.GET_ACCOUNT_BY_USER_ID}${userId}`,
          config,
          );
      console.log("success fetch")
      if (response.status === HttpStatusCode.Ok) {
        console.log("OK")
        setAccount(response.data);
        console.log(response.data);
      }
      } catch (error) {
        // console.error("fetch account error:", error);
        console.log("ERROR")
      }
    }
  };

  const handleTransaction = async () => {
    try {
      setTransactionAdd((prevState) => ({
        ...prevState,
        timestamp: new Date(),
      }));

      console.log("sending:", transactionAdd);
      const response = await axios.post(
        endpoints.ADD_TRANSACTION,
        transactionAdd
      );
      console.log("reponse from server", response.data);
    } catch (error) {
      // console.error("Error:", error);
    }

    setTransactionAdd((prevState) => ({
      ...prevState,
      title: "",
      receiverAccountNumber: "",
      amount: 0,
    }));
    setValueView("");
    fetchTransactionList();
  };

  // useEffect(() => {
  //   setToken(localStorage.getItem("jwt") || "")
  // }, []);
  
  
  useEffect(() => {
    fetchAccountData();
    // fetchTransactionList();
  }, [token]);


  
  return (
    <>
      <MyNavbar />
      <Container className="text-light pt-2">
        <Row>
          <Col className="text-end h6">{username}</Col>
        </Row>
        <Row>
          <Col className="text-end h3">Balance: {account?.balance}</Col>
        </Row>
        <Row>
          <Form>
            <Row className="justify-content-around my-5">
              <FormFloating className="text-dark col-lg-4 col-12 mb-4">
                <FormControl
                  value={transactionAdd.receiverAccountNumber}
                  onChange={(e) =>
                    setTransactionAdd((prevState) => ({
                      ...prevState,
                      receiverAccountNumber: e.target.value,
                    }))
                  }
                  type="text"
                  id="receiver-number"
                  placeholder="Receiver account number"
                />
                <FormLabel htmlFor="To" className="ms-2">
                  {" "}
                  Receiver number{" "}
                </FormLabel>
              </FormFloating>
              <FormFloating className="text-dark col-lg-2 col-12 mb-4">
                <FormControl
                  value={transactionAdd.title}
                  onChange={(e) =>
                    setTransactionAdd((prevState) => ({
                      ...prevState,
                      title: e.target.value,
                    }))
                  }
                  type="text"
                  id="title"
                  placeholder="Amount to transfer"
                />
                <FormLabel htmlFor="title" className="ms-2">
                  {" "}
                  Title{" "}
                </FormLabel>
              </FormFloating>

              <FormFloating className="text-dark col-lg-2 col-12 mb-4">
                <FormControl
                  value={valueView}
                  onChange={handleAmountChange}
                  type="text"
                  id="amount"
                  placeholder="Amount to transfer"
                />
                <FormLabel htmlFor="amount" className="ms-2">
                  {" "}
                  Amount{" "}
                </FormLabel>
              </FormFloating>

              <Button
                onClick={handleTransaction}
                className="btn-primary col-lg-3 col-11 mb-4"
              >
                Send
              </Button>
            </Row>
          </Form>
        </Row>
        <hr />
        <Container>
          <Row>
            <Col className="text-start h3">Transaction history</Col>
          </Row>
          <Row>
            <Col className="text-start h4">Select interval:</Col>
          </Row>

          <Form>
            <Row className="mt-2 mb-1 justify-content-evenly">
              <Col className="col-md-3 col-5">
                <Row>
                  <label htmlFor="after" className="form-label">
                    After
                  </label>
                </Row>
                <Row>
                  <input
                    type="date"
                    id="after-date"
                    className="form-control-lg"
                    placeholder="Transaction after date"
                  />
                </Row>
              </Col>
              <Col className="col-md-3 col-5">
                <Row>
                  <label htmlFor="before" className="form-label">
                    Before
                  </label>
                </Row>
                <Row>
                  <input
                    type="date"
                    id="before-date"
                    className="form-control-lg"
                    placeholder="Transaction before date"
                  />
                </Row>
              </Col>
            </Row>
          </Form>

          <Container className="background-color-container mt-5 mb-5 border border-1 border-white rounded-3">
            <Table className="text-light">
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Type</th>
                  <th>From/To</th>
                  <th>Title</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody className="table-group-divider">
                {transactionViews.map((transactionView) => (
                  <TransactionView
                    key={transactionView.id}
                    transaction={transactionView}
                  />
                ))}
              </tbody>
            </Table>
          </Container>
        </Container>
      </Container>
    </>
  );
}
