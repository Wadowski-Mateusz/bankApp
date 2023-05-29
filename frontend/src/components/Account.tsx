import Table from 'react-bootstrap/Table';
import MyNavbar from "./MyNavbar";
import Footer from "./Footer";

export default function Account() {
  return (
    <>
    <MyNavbar />
    <div className="container text-light pt-2">
        <div className="row"><div className="col text-end h6">Karol Karolowski</div></div>
        <div className="row"><div className="col text-end h3">Balance: 12345.12</div></div>
        <form className="row">
          <div className="row justify-content-around mt-5 mb-5">
            <div className="form-floating text-dark col-md-3 col-5">
              <input type="text" id="receiver-number" className="form-control" placeholder="Receiver account number" />
              <label htmlFor="To" className="form-label ms-2">Receiver number</label>
            </div>
            <div className="form-floating text-dark col-md-3 col-5">
                <input type="text" id="amount" className="form-control" placeholder="Amount to transfer" />
                <label htmlFor="amount" className="form-label ms-2">Amount</label>
            </div>
            <button className="btn btn-primary col-lg-3 col-2">Send</button>
          </div>
        </form>
        <hr />
        <div className="row"><div className="col text-start h3">Transaction history</div></div>
        <div className="row"><div className="col text-start h4">Select interval:</div></div>

        <form>
            <div className="row mt-2 mb-1 justify-content-evenly">
                <div className="col-md-3 col-5 ms-3">
                    <div className="row"><label htmlFor="after" className="form-label">After</label></div>
                    <div className="row"><input type="date" id="after-date" className="form-control-lg" placeholder="Transaction after date"/></div>
                </div>
                <div className="col-md-3 col-5 ms-3">
                    <div className="row"><label htmlFor="before" className="form-label">Before</label></div>
                    <div className="row"><input type="date" id="before-date" className="form-control-lg" placeholder="Transaction before date"/></div>
                </div>
            </div>
        </form>


        <div className="container background-color-container mt-5 mb-5 border border-1 border-white rounded-3">
            <Table className="text-light">
                <thead>
                    <th>Date</th>
                    <th>Type</th>
                    <th>From/To</th>
                    <th>Title</th>
                    <th>Amount</th>
                </thead>
                <tbody className="table-group-divider">
                    <tr>
                        <td>2023-04-12 03:23 PM</td>
                        <td>Incoming</td>
                        <td>Mom</td>
                        <td>For living</td>
                        <td>123.52</td>
                    </tr>
                    <tr>
                        <td>2023-04-12 03:23 PM</td>
                        <td>Outgoing</td>
                        <td>Marsupium</td>
                        <td>Loan for live</td>
                        <td>-465.99</td>
                    </tr>                    
                    <tr>
                        <td>2023-04-12 03:23 PM</td>
                        <td>Incoming</td>
                        <td>Dad</td>
                        <td>For living</td>
                        <td>50.00</td>
                    </tr>
                    <tr>
                        <td>2023-04-12 03:23 PM</td>
                        <td>Incoming</td>
                        <td>Mom</td>
                        <td>For living</td>
                        <td>123.52</td>
                    </tr>
                </tbody>
            </Table>
        </div>
    </div>
    <Footer />
    </>
  );
}