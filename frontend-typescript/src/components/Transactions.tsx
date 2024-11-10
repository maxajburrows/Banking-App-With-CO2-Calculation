import axios from "axios";

import {useParams} from "react-router-dom";
import {Transaction} from "../types/Transaction.ts";
import {useEffect, useState} from "react";


function Transactions() {
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const baseUrl: string = "http://localhost:8080/transactions/";
    const requestHeaders: object = {"Authorization": `Bearer ${localStorage.getItem("token")}`};
    const {iban} = useParams();

    async function fetchTransactions() {
        try {
            const response: Transaction[] = (await axios.get(`${baseUrl}${iban}`, {headers: requestHeaders})).data;
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
        <section className="background-radial-gradient overflow-hidden vh-100 justify-content-center">
            <div className="container px-4 px-md-5 text-center text-lg-start my-5">
                <div className="row gx-lg-5 align-items-center mb-5">
                    <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>Transactions</h1>
                    {transactions.map((transaction) => (
                        <button type="button" className="btn btn-outline-dark btn-lg btn-block">
                            <p>{transaction.toBankAccount}</p>
                            <p>{transaction.transactionType}</p>
                            <p>{transaction.amount}</p>
                        </button>
                    ))}
                </div>
            </div>
        </section>
    )
}

export default Transactions
