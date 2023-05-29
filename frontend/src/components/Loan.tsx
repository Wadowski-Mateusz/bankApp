interface Props {
    id: string,
    name: string,
    dateFrom: string,
    dateTo: string,
    interest: number,
    loanDue: number,
}

export default function Loan({id, name, dateFrom, dateTo, interest, loanDue }: Props) {
 return (
    <div className="row p-3 bg-primary rounded-4 m-2 d-flex justify-content-center flex-column">
            <span className="d-flex justify-content-around">
              <span>
                <span className="fw-bold">Name</span>:{" "}
                <span id="loan-name">{ name }</span>
              </span>
              <span>
                <span className="fw-bold">From</span>:{" "}
                <span id="loan-from">{ dateFrom }</span>
              </span>
              <span>
                <span className="fw-bold">To</span>:{" "}
                <span id="loan-to">{ dateTo }</span>
              </span>
              <span>
                <span className="fw-bold">Interets</span>:{" "}
                <span id="loan-interest">{ interest }%</span>
              </span>
              <span>
                <span className="fw-bold">To be repaid</span>:{" "}
                <span id="loan-due">{ loanDue }</span>
              </span>
            </span>
          </div>
 )   
}