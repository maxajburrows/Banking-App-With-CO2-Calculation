import axios from "axios";

import {useEffect, useState} from "react";
import {Account} from "./types/Account.ts";
import {User} from "./types/User.ts";


function Homepage() {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const baseUrl : string = "http://localhost:8080/users/";
    const username : string | null = localStorage.getItem("username");
    const password : string | null = localStorage.getItem("password");
    const encodedCredentials : string = btoa(`${username}:${password}`);
    const requestHeaders : object = { "Authorization": `Basic ${encodedCredentials}` };

    async function fetchAccountData() {
        try {
            const response  = await axios.get(`${baseUrl}${username}`, { headers: requestHeaders });
            console.log(response);
        } catch (e) {
            console.error(e);
        }
        //setAccounts(await response.json());
    }

    useEffect(() => {
        fetchAccountData();
    }, []);
  return (
    <>
      <h1>My Accounts</h1>
        {accounts.map((account) => (
                <button type="button" className="btn btn-outline-dark btn-lg btn-block" >
                    <h2>{account.name}</h2>
                    <p>{account.iban}</p>
                </button>
        ))}
    </>
  );
}

export default Homepage;
