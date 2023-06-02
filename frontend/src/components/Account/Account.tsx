import {
  FormFloating,
  Row,
  Col,
  Container,
  Button,
  Form,
} from "react-bootstrap";
import Table from "react-bootstrap/Table";
import React, { useState, useEffect } from "react";
import axios from "axios";

import MyNavbar from "../Nav/MyNavbar";
import TransactionView from "./TransactionView";
import { TransactionViewDTO } from "../DTOs/TransactionDTOs";
import * as TransactionEndpoint from "../../endpoints/transactionEndpoints";

interface Transaction {
  id: string;
  date: string;
  type: string;
  from: string;
  title: string;
  amount: string;
}

export default function Account() {
  const IDtoDelete = "5151f90e-ce44-4784-bb73-26601cb2cbd9";

  const [transactionViews, setTransactionViews] = useState<
    TransactionViewDTO[]
  >([]);
  const fetcTransactionList = async () => {
    try {
      const userId = IDtoDelete;
      const response = await axios.get(
        `${TransactionEndpoint.GET_ALL_TRANSFERS_OF_USER}${userId}`
      );
      const data = response.data;
      setTransactionViews(data);
      console.log(data);
    } catch (error) {
      console.error("fetch loans error:", error);
    }
  };

  useEffect(() => {
    fetcTransactionList();
  }, []);
  return (
    <>
      <MyNavbar />
      <Container className="text-light pt-2">
        <Row>
          <Col className="text-end h6">Karol Karolowski</Col>
        </Row>
        <Row>
          <Col className="text-end h3">Balance: 12345.12</Col>
        </Row>

        <Form className="row">
          <Row className="justify-content-around mt-5 mb-5">
            <FormFloating className="text-dark col-lg-4 col-md-4 col-12 mb-1">
              <input
                type="text"
                id="receiver-number"
                className="form-control"
                placeholder="Receiver account number"
              />
              <label htmlFor="To" className="form-label ms-2">
                Receiver number
              </label>
            </FormFloating>
            <FormFloating className="text-dark col-lg-4 col-md-4 col-12 mb-4">
              <input
                type="text"
                id="amount"
                className="form-control"
                placeholder="Amount to transfer"
              />
              <label htmlFor="amount" className="form-label ms-2">
                Amount
              </label>
            </FormFloating>
            <Button className="btn-primary col-lg-3 col-md-2 col-11 mb-4">
              Send
            </Button>
          </Row>
        </Form>

        <hr />

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
    </>
  );
}
