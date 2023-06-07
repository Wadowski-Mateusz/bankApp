import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import axios, { HttpStatusCode } from "axios";

import {AddressDTO} from "../../DTOs/AddressDTO"
import {UserVerificationDTO, UserVerifyOrBanDTO} from "../../DTOs/UserDTOs"
import * as ENDPOINT from "../../endpoints/endpoints"
import { States } from "./States";

interface Props {
  setStateInPanel: (state: number) => void,
}

enum Action{
  Waiting,
  Panel,
  Ban,
  Verify,
}

const emptyAddress: AddressDTO = {
  country: "",
  sector: "",
  city: "",
  street: "",
  number: "",
  zip: "",
}

const userDataEmpty: UserVerificationDTO = {
  userId: "",
  fullName: "",
  email: "",
  birthday: "",
  addressDTO: emptyAddress,
  idNumber: "",
}

const verInit: UserVerifyOrBanDTO = {
  userId: "",
  verified: false,
}



export default function Verification ({ setStateInPanel }: Props) {
  const token = localStorage.getItem("jwt") || "";
  const [helper, setHelper] = useState(Action.Waiting);
  const [userToVerifyDataTxt, setUserToVerifyDataTxt] = useState<UserVerificationDTO>();
  const [idScan, setIdScan] = useState<Blob | null>(null);
  const [verificationDTO, setVerificationDTO] = useState<UserVerifyOrBanDTO>(verInit)

  // fetch user id afret user  is fetched
  useEffect(() => {
    const fetchUserIdScan = async () => {
      if(userToVerifyDataTxt != userDataEmpty) {
        try {
          const config = {
            headers: { Authorization: `Bearer ${token}` },
            responseType: 'arraybuffer'
          };
          const response = await axios.get(
            `${ENDPOINT.GET_USER_TO_VERIFY_SCAN}${userToVerifyDataTxt?.userId}`,
            config
          );
          // const response = await axios.get(
          //   `${ENDPOINT.GET_USER_TO_VERIFY_SCAN}${userToVerifyDataTxt?.userId}`,
          //   { responseType: 'arraybuffer', }
          // );
          if (response.status === HttpStatusCode.Ok) {
            const dataBlob = new Blob([response.data], { type: 'image/bmp' });
            setIdScan(dataBlob);
            // console.log(data)
            // TODO NO USERS TO VERIFY
          } else {
            // Some error
            // setIdScan(null)
          }
            
        } catch (error) {
          console.error('fetch error:', error);
        }
      }
    }
    if(userToVerifyDataTxt) {
      fetchUserIdScan();
    }

  }, [userToVerifyDataTxt])


  const fetchUserToVerification = async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${token}` }
      };
      const response = await axios.get(
        ENDPOINT.GET_USER_TO_VERIFY_DATA,
        config
      );
      if(response.status === HttpStatusCode.Ok) {
        const data: UserVerificationDTO = response.data;

        setUserToVerifyDataTxt(data);
        console.log(data)

        // TODO NO USERS TO VERIFY
      } else {
        // Some HTTP error code
        setUserToVerifyDataTxt(userDataEmpty);
        setIdScan(null);
      }
        
    } catch (error) {
      // console.error('fetch error:', error);
    }
  };

  useEffect(() => {
    fetchUserToVerification();
  }, []);


  // PUT verificatoin
  useEffect(() => {

    const sendRequestWithVerification = async () => {
      if(verificationDTO !== verInit) {
          // console.log("inside")
        try {
          console.log("verification using", verificationDTO.userId)
          const config = {
            headers: { Authorization: `Bearer ${token}` }
          };
          const response = await axios.put(ENDPOINT.VERIFY_USER, verificationDTO, config);
          if(response.status === HttpStatusCode.Ok) {
            console.log(response)
            setVerificationDTO(verInit);
            setIdScan(null);
            // setUserToVerifyDataTxt(userDataEmpty);
            fetchUserToVerification();
          } else {
            // TODO?
          }
        } catch (error) {
          console.error('error:', error);
        }
      } else {
        console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      }
    };


    if(verificationDTO !== verInit) {
      sendRequestWithVerification() 
      setVerificationDTO(verInit);
    }
  }, [verificationDTO])


  useEffect(() => {
    const setVer = (verificationStaus: boolean) => {
      setVerificationDTO((prevState) => ({
        ...prevState,
        userId: userToVerifyDataTxt?.userId || "",
        verified: verificationStaus,
      }))
    };

    if (helper != Action.Waiting) {
      switch(helper) {
        case Action.Panel:
          setStateInPanel(States.Default)
          break;
        case Action.Ban:
          if(userToVerifyDataTxt)
            setVer(false);
          break;
        case Action.Verify:
          if(userToVerifyDataTxt)
            setVer(true);
          break;
        default:
          console.log("Verification - this should never happen - switch");
      }
      setHelper(Action.Waiting);
    }

  }, [helper])


  return (
    <>
      <Container
        id="panel-container"
        className="
        background-color-container 
        py-3 px-4 rounded-5 
        border border-white border-1 
        d-flex flex-column justify-content-between 
        h-auto"
        >
        {/* Top 3 rows */}
        <Row className="gap-3">
          <Col>
          <Row className="bg-primary rounded-4 my-3 p-1"><span>Name: {userToVerifyDataTxt?.fullName}</span></Row>
          <Row className="bg-primary rounded-4 my-3 p-1"><span>email:<br/>{userToVerifyDataTxt?.email}</span></Row>
          <Row className="bg-primary rounded-4 my-3 p-1"><span>Birthday: {userToVerifyDataTxt?.birthday}</span></Row>
          </Col>

          <Col>
          <Row className="bg-primary rounded-4 my-3 p-1">
            {userToVerifyDataTxt?.addressDTO &&
              <>
                <span className="text-center">
                  {userToVerifyDataTxt?.addressDTO.country}
                </span>
                <span className="text-center">
                  {`${userToVerifyDataTxt?.addressDTO.sector} ${userToVerifyDataTxt?.addressDTO.city}`}
                </span>
                <span className="text-center">
                  {`${userToVerifyDataTxt?.addressDTO.street} ${userToVerifyDataTxt?.addressDTO.number}`}
                </span>
                <span className="text-center">
                  {userToVerifyDataTxt?.addressDTO.zip}
                </span>
              </>
            }
          </Row>
          <Row className="bg-primary rounded-4 my-3 p-1"><span>ID number:<br/>{userToVerifyDataTxt?.idNumber}</span></Row>
          </Col>
        </Row>


        <Row className="h1">
          { (idScan) && (<img src={URL.createObjectURL(idScan)} alt="Scan of client ID" style={{ maxHeight: '300px', maxWidth: '1500px'}}/>) }
          { !idScan && (userToVerifyDataTxt) && <span> Scan is loading...</span>}
          { !idScan && (!userToVerifyDataTxt || userToVerifyDataTxt === userDataEmpty) && <span> No clients to verify </span>}
          
        </Row>

        <Row className="gap-1 mt-4">
          <Col type="button" onClick={() => setHelper(Action.Panel)} className="btn btn-primary rounded-4">
            <span>Panel</span>
          </Col>
          <Col type="button" onClick={() => setHelper(Action.Ban)} className="btn btn-primary rounded-4">
            <span>Ban</span>
          </Col>
          <Col type="button" onClick={() => setHelper(Action.Verify)} className="btn btn-primary rounded-4">
            <span>Verify</span>
          </Col>
        </Row>

      </Container>
    </>
  );
}
