import { useEffect, useState } from "react";
import axios, {AxiosRequestConfig} from "axios";
import * as endpoints from "./endpoints/endpoints";
import { AccountDTO } from "./components/DTOs/AccountDTO";


export default function Test() {
  const userId = localStorage.getItem("userId") || "";
  const [account, setAccount] = useState<AccountDTO>();
  const [token, setToken] = useState<String>(localStorage.getItem("jwt") || "");


  async function fetchAccountData() {
    if(token !== undefined) {
      try{
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        console.log("before fetch: ", config)
        console.log("before fetch: ", token)
        const response = await axios.get(
          `${endpoints.GET_ACCOUNT_BY_USER_ID}${userId}`,
          config,
          );
          console.log("SUCCESS")
          setAccount(response.data);
          console.log("SUCCESS assign")
        } catch(error) {
          console.log("test error")
          console.error(error)
        }
      }

  };

  useEffect(() => {
    fetchAccountData();
    // console.log("token: ", localStorage.getItem("jwt"))
    // console.log("token2: ", token)
  }, [token]);


  useEffect(() => {
    // setToken(localStorage.getItem("jwt") || "")
  }, []);

  return (
    <>
      <p>
        {account?.number}
         test
      </p>
    </>
  );
}
