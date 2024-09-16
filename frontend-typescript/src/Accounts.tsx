import {useEffect, useState} from "react";
import {Account} from "./types/Account.ts";


function Accounts() {
    const [accounts, setAccounts] = useState<Account[]>([]);

    async function fetchAccountData() {
        // Use axios for fetching data
        const response = await fetch("http://localhost:8080/accounts");
        if (!response.ok) {
            throw Error("Can't get accounts")
        }
        console.log("ran use effect")
        setAccounts(await response.json());
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

export default Accounts;
