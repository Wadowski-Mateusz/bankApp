import MyNavbar from "../Nav/MyNavbar";
import Footer from "../Nav/Footer";

export default function Settings() {
  return (
    <>
    <MyNavbar />
    <div className="container d-flex justify-content-center align-items-center h5" style={{height: "50%"}}>
        <div className="d-flex flex-column align-items-center col-6">

            <form id="form-settings" className="p-5 mt-5 d-flex flex-column">
                <div className="form-check form-switch form-check-inline m-2">
                    <label htmlFor="email-sub" className="form-check-label">Email subscription</label>
                    <input type="checkbox" id="email-sub" className="form-check-input" value="0" />
                </div>
                <button type="button" className="btn btn-primary btn-lg m-2">Save</button>
            </form>

            <form id="form-delete-acc" className="p-1">
                <button type="button" className="btn btn-primary btn-lg">Delete account</button>
            </form>

        </div>
        
    </div>
    <Footer />
    </>
  );
}