import React, { ChangeEvent, useEffect, useState } from "react";
import {Container, Row, Col} from 'react-bootstrap'
import axios, { HttpStatusCode } from 'axios';

import SubCheckbox from "./SubCheckbox";
import MyNavbar from "../Nav/MyNavbar";
import {GET_USER_SETTINGS_ENDPOINT, SEND_USER_SETTINGS_ENDPOINT, DELETE_ACCOUNT_ENDPOINT} from "../../endpoints/settingsEndpoints"
import { useNavigate } from "react-router-dom";

interface SettingsDTO {
  id: string,
  emailSubscription: boolean | undefined,
  userId: string,
}

interface DeleteDTO {
  id: string,
  password: string
}


export default function Settings() {

  const settingsInit: SettingsDTO = {
    id: "",
    emailSubscription: false,
    userId: "",
  }

  const navigate = useNavigate();
  const token = localStorage.getItem("jwt") || "";
  const userId = localStorage.getItem("userId") || "";
  const [settings, setSettings] = useState<SettingsDTO>(settingsInit);
  const [deleteAccView, setDeleteAccView] = useState(false);
  const [switchValue, setSwitchValue] = useState<boolean>();
  // const [settingsId, setSettingsId] = useState<string>();
  const [password, setPassword] = useState("");
  const [dataFetch, setDataFetch] = useState(false)


  useEffect(() => {
    if(token && userId) {
      fetchSettings()
    }
  }, [token, userId]);


  async function fetchSettings() {
    try {
      const config = {
        headers: { Authorization: `Bearer ${token}` }
      };
      const response = await axios.get(
        `${GET_USER_SETTINGS_ENDPOINT}${userId}`,
         config
      );
      const data: SettingsDTO = response.data;
      console.log("fetched: ", data)
      setSwitchValue(data.emailSubscription)
      setSettings(data);
      console.log("set: ", settings)
        
    } catch (error) {
      console.error('fetch error:', error);
    }
  };

  


// send settings
async function handleSavingSettings() {

  try {
    if(settings.emailSubscription != switchValue)
      setSettings((prevState) => ({
        ...prevState, 
        emailSubscription: switchValue,
      }))
      const config = {
        headers: { Authorization: `Bearer ${token}` }
      };
    const response = await axios.put(
      SEND_USER_SETTINGS_ENDPOINT,
       settings,
       config);
    console.log('reponse from server', response.data);
    fetchSettings()
  } catch (error) {
    console.error('Error:', error);
  }

};


async function deleteAccount(e: React.FormEvent<HTMLFormElement>) {

  e.preventDefault();
  setDeleteAccView(!deleteAccView);
  const deletedto: DeleteDTO = {
    id: userId,
    password: password
  };

  try {
    console.log("del:", deletedto)
    const headers = { Authorization: `Bearer ${token}` };
    const response = await axios.delete(
      DELETE_ACCOUNT_ENDPOINT, 
      {
        headers: headers,
        data: deletedto, 
      }
    );
    setPassword("")
    if(response.status === 200) {
      navigate("/");
    }
  } catch (error) {
    console.error('Error del:', error);
  }

}

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
                <SubCheckbox
                 chckVal={settings.emailSubscription} 
                 onChange={() =>   
                  setSettings((prevState) => ({
                  ...prevState, 
                  emailSubscription: !settings.emailSubscription,
                }))} 
                 />
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
                  <span className="text-center text-white">Are you sure you want to delete account?</span>
              
                  {/* <div className="row mt-2">
                    <input value={password} onChange={(e) => setPassword(e.target.value)}
                    type="password" className="rounded-2" placeholder="password" />
                  </div> */}
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