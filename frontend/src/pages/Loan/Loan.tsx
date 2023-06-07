import { LoanDTO} from "../../DTOs/LoanDTOs"
import { Container, Row, Col } from "react-bootstrap";

export default function Loan({id, name, dateFrom, dateTo, interest, amount, due }: LoanDTO) {
 return (
    <Container className="row p-2 bg-primary rounded-4 m-2 d-flex justify-content-center flex-column">
      <Row className="d-flex justify-content-around">
              <Col className="col-lg-auto col-md-5 col-12">
                <span className="fw-bold">Name</span>:{" "}
                <span id="loan-name">{ name }</span>
              </Col>
              <Col className="col-lg-auto col-md-5 col-12">
                <span className="fw-bold">From</span>:{" "}
                <span id="loan-from">{ dateFrom.toLocaleString() }</span>
              </Col>
              <Col className="col-lg-auto col-md-5 col-12">
                <span className="fw-bold">To</span>:{" "}
                <span id="loan-to">{ dateTo.toLocaleString() }</span>
              </Col>
              <Col className="col-lg-auto col-md-5 col-12">
                <span className="fw-bold">Interets</span>:{" "}
                <span id="loan-interest">{ interest }%</span>
              </Col>
              <Col className="col-lg-auto col-md-5 col-12">
                <span className="fw-bold">To be repaid</span>:{" "}
                <span id="loan-due">{ due }</span>
              </Col>
            </Row>
      </Container>
 )   
}