import axios from "axios";

import { useParams } from "react-router-dom";
import {Transaction} from "./types/Transaction.ts";
import {useEffect, useState} from "react";


function Transactions() {

    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const baseUrl : string = "http://localhost:8080/transactions/";
    const username : string | null = localStorage.getItem("username");
    const password : string | null = localStorage.getItem("password");
    const encodedCredentials : string = btoa(`${username}:${password}`);
    const requestHeaders : object = { "Authorization": `Basic ${encodedCredentials}` };
    const { iban } = useParams();


    async function fetchTransactions() {
        try {
            const response : Transaction[]  = (await axios.get(`${baseUrl}${iban}`, { headers: requestHeaders })).data;
            setTransactions(response)
            console.log(response);
        } catch (e) {
            console.error(e);
        }
    }

    useEffect(() => {
        fetchTransactions();
    }, []);

    return (
        <div>
            <h1>Transactions</h1>
            {transactions.map((transaction) => (
                <button type="button" className="btn btn-outline-dark btn-lg btn-block">
                    <h2>{transaction.amount}</h2>
                    <p>{transaction.transactionId}</p>
                </button>
            ))}
        </div>
    )
}

export default Transactions
