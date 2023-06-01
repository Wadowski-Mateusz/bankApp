import React, { ChangeEvent, useEffect, useState } from "react";
import {Container, Row, Col} from 'react-bootstrap'
import axios from 'axios';

import SubCheckbox from "./SubCheckbox";
import MyNavbar from "../Nav/MyNavbar";
import {GET_USER_SETTINGS_ENDPOINT, SEND_USER_SETTINGS_ENDPOINT, DELETE_ACCOUNT_ENDPOINT} from "../../endpoints/settingsEndpoints"
import { useNavigate } from "react-router-dom";

interface SettingsDTO {
  id: string | undefined,
  emailSubscription: boolean | undefined,
  userId: string,
}

interface DeleteDTO {
  id: string,
  password: string
}

const IDtoDelete = "5151f90e-ce44-4784-bb73-26601cb2cbd9";


export default function Settings() {

  const [switchValue, setSwitchValue] = useState<boolean>();
  const [settingsId, setSettingsId] = useState<string>();
  const [password, setPassword] = useState("");

  const [deleteAccView, setDeleteAccView] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSettings = async () => {
      try {
        const userId = IDtoDelete;
        const response = await axios.get(`${GET_USER_SETTINGS_ENDPOINT}${userId}`);
        const data: SettingsDTO = response.data;

        setSwitchValue(data.emailSubscription);
        setSettingsId(data.id)
      } catch (error) {
        console.error('fetch error:', error);
      }
    };
    fetchSettings();
  }, []);


// send settings
const handleSavingSettings = async () => {
  const settingsToSave: SettingsDTO = {
    id: settingsId,
    emailSubscription: switchValue,
    userId: IDtoDelete,
  };

  try {
    const response = await axios.put(SEND_USER_SETTINGS_ENDPOINT, settingsToSave);
    console.log('reponse from server', response.data);
  } catch (error) {
    console.error('Error:', error);
  }

};


async function deleteAccount(e: React.FormEvent<HTMLFormElement>) {



  e.preventDefault();
  setDeleteAccView(!deleteAccView);
  const deletedto: DeleteDTO = {
    id: IDtoDelete,
    password: password
  };

  console.log(deletedto)

  try {
    const response = await axios.delete(DELETE_ACCOUNT_ENDPOINT, {data: deletedto});


    console.log('reponse from server', response.data);
    setPassword("")
    if(response.status === 200)
      navigate("/");
  } catch (error) {
    console.error('Error:', error);
  }

}


const handleCheckboxChange = (event: ChangeEvent<HTMLInputElement>) => {
  console.log("before:", switchValue, "after", event.target.checked)
  setSwitchValue(event.target.checked);
};


function handleDeleteView(e: React.FormEvent<HTMLFormElement>) {
  e.preventDefault();
  setDeleteAccView(!deleteAccView);
}



  return (
    <>
    <MyNavbar />
    
    <div className="container d-flex justify-content-center align-items-center h5" style={{height: "50%"}}>
        <div className="d-flex flex-column align-items-center col-6">

           {!deleteAccView && <>
            <form id="form-settings" className="p-5 mt-5 d-flex flex-column">
                <SubCheckbox chckVal={switchValue} onChange={handleCheckboxChange}/>
                <button type="button" onClick={handleSavingSettings} className="btn btn-primary btn-lg m-2">Save</button>
            </form>


            <form onSubmit={handleDeleteView} id="form-delete-acc" className="p-1">
                <button 
                  className="btn btn-primary btn-lg">
                    Delete account
                </button>
            </form>
            </>}

            {deleteAccView &&
              <Container className="p-5 mt-5">
                <form onSubmit={deleteAccount}>           
                  <Row className="d-flex flex-column justify-content-center align-items-center">
                  <span className="text-center text-white">To delete account enter password:</span>
              
                  <div className="row mt-2">
                    <input value={password} onChange={(e) => setPassword(e.target.value)}
                    type="password" className="rounded-2" placeholder="password" />
                  </div>
                  </Row>
                  <Row className="d-flex flex-column justify-content-center align-items-center">
                  <button className="btn btn-primary btn-lg mt-2 col-lg-6 col-12"> Confirm </button>
                  </Row>
                </form>

                <form onSubmit={handleDeleteView} className="p-5 mt-5 d-flex flex-column row justify-content-center align-items-center">
                          <button 
                            className="btn btn-primary btn-lg col-lg-6 col-12 row">
                              Back
                          </button>
                      </form>
                </Container>}
          </div>
    </div>
    </>
  );
}