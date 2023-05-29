import MyNavbar from "./MyNavbar";
import Footer from "./Footer";

export default function PanelEmp() {
  return (
    <>
      <MyNavbar />

      <div className="d-flex align-items-center vh-100">
        <div className="container text-light col-lg-6 col-8 h-75 d-flex align-items-center">
          <div
            id="panel-container"
            className="container background-color-container py-3 px-4 rounded-5 border border-white border-1 d-flex flex-column justify-content-between h-auto"
          >
            <div id="user-name" className="row">
              {" "}
              Stan Konwalski{" "}
            </div>
            <div
              id="buttons-container"
              className="container d-flex flex-column"
            >
              <div className="row mt-3 justify-content-evenly">
                <button
                  type="button"
                  className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                >
                  Register client
                </button>
                <button
                  type="button"
                  className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                >
                  Verify clients
                </button>
              </div>
              <div className="row mt-3 justify-content-evenly">
                <button
                  type="button"
                  className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                >
                  Add announcement
                </button>
                <button
                  type="button"
                  className="btn btn-primary btn-lg col-lg-5 col-12 rounded-4 m-1"
                >
                  Delete announcement
                </button>
              </div>
            </div>
            <div className="row mt-3 d-flex align-content-end">
              <button
                type="button"
                className="btn btn-primary col-xl-2 col-lg-3 col-12 btn-lg rounded-4"
              >
                Log out
              </button>
            </div>
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
}
