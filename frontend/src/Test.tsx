import { useEffect, useState } from "react";
import axios, { HttpStatusCode } from "axios";
import * as ENDPOINT from "./endpoints/endpoints"

export interface AddressDTO {
  country: string,
  sector: string,
  city: string,
  street: string,
  number: string,
  zip: string,
}

export interface UserVerificationDTO {
  userId: string,
  fullName: string,
  email: string,
  birthday: string,
  addressDTO: AddressDTO,
  idNumber: string
}

export interface UserVerifyOrBanDTO {
  userId: string,
  verified: boolean
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

const initVer: UserVerifyOrBanDTO = {
  userId: "",
  verified: false,
}

export default function Verification () {

  const [pressed, setPressed] = useState("");

  const [userToVerifyDataTxt, setUserToVerifyDataTxt] = useState<UserVerificationDTO>();
  const [idScan, setIdScan] = useState<Blob | null>(null);
  const [verificationDTO, setVerificationDTO] = useState<UserVerifyOrBanDTO>(initVer)


  useEffect(() => {
    const fetchUserIdScan = async () => {
      if(userToVerifyDataTxt != userDataEmpty) {
        try {
          const response = await axios.get(`${ENDPOINT.GET_USER_TO_VERIFY_SCAN}${userToVerifyDataTxt?.userId}`,{
            responseType: 'arraybuffer',
          });
          if (response.status === HttpStatusCode.Ok) {
            const dataBlob = new Blob([response.data], { type: 'image/bmp' });
            setIdScan(dataBlob);
          }
        } catch (error) {
          console.error('fetch error:', error);
        }
      }
    }
    fetchUserIdScan();
    setVerificationDTO((prevState) => ({
      ...prevState,
      userId: userToVerifyDataTxt?.userId || "",
    }))
  }, [userToVerifyDataTxt])

  useEffect(() => {
    const fetchUserToVerification = async () => {
      try {
        const response = await axios.get(ENDPOINT.GET_USER_TO_VERIFY_DATA);
        if(response.status === HttpStatusCode.Ok) {
          const data: UserVerificationDTO = response.data;
          setUserToVerifyDataTxt(data);
          console.log(data)
        } else {
          setUserToVerifyDataTxt(userDataEmpty)
        }
      } catch (error) {
        console.error('fetch error:', error);
      }
    };
    fetchUserToVerification();
  }, []);


  useEffect (() => {
      const killMe = async () => {
        setVerificationDTO(initVer);
      }

      if(verificationDTO != initVer) {
        console.log("kill me", verificationDTO)
        killMe();
      }
  }, [verificationDTO])

  useEffect(() => {
    const verifyOrNot = async (status: boolean) => {
      setVerificationDTO((prevState) => ({
        ...prevState,
        userId: userToVerifyDataTxt?.userId || "",
        verified: status,
      }))
    };
    if(pressed !== "") {
      if(pressed==="ban")
        verifyOrNot(false);
      else if (pressed === "ver")
        verifyOrNot(true);
      setPressed("");
      console.log(verificationDTO)
    }
  }, [pressed])

  return (
    <>
      <button type="button" name ="ban" value={0} onClick={(e) => setPressed("ban")}>
        ban
      </button>
      <button type="button" name="ver" value={1} onClick={(e) => setPressed("ver")}>
        ver
      </button>
    </>
  );
}
