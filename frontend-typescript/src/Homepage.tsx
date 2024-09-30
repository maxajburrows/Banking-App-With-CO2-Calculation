import axios from "axios";

import {useEffect, useState} from "react";
import {Account} from "./types/Account.ts";
import {User} from "./types/User.ts";
import {useNavigate} from "react-router-dom";


function Homepage() {
    const [usersName, setUsersName] = useState<string>("");
    const [accounts, setAccounts] = useState<Account[]>([]);
    const baseUrl : string = "http://localhost:8080/users/";
    const username : string | null = localStorage.getItem("username");
    const password : string | null = localStorage.getItem("password");
    const encodedCredentials : string = btoa(`${username}:${password}`);
    const requestHeaders : object = { "Authorization": `Basic ${encodedCredentials}` };
    const navigate = useNavigate();


    async function fetchAccountData() {
        try {
            const response : User  = (await axios.get(`${baseUrl}${username}`, { headers: requestHeaders })).data;
            setUsersName(response.firstName)
            setAccounts(response.bankAccounts);
            console.log(response);
        } catch (e) {
            console.error(e);
        }
    }

    function goToAccount(iban: string){
        navigate(`/accounts/${iban}`);
    }


    useEffect(() => {
        fetchAccountData();
    }, []);
  return (
      <>
          <h1>Welcome back {usersName}</h1>

          <h1>Your Accounts</h1>
          {accounts.map((account) => (
              <button type="button" className="btn btn-outline-dark btn-lg btn-block" onClick={() => goToAccount(account.iban)}>
                  <h2>{account.accountName}</h2>
                  <p>{account.iban}</p>
              </button>
          ))}
      </>
  );
}

export default Homepage;
