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
                        <div className="card bg-glass">
                            <div className="card-body bg-glass row px-4 py-5 px-md-5">
                                <div className="col">{transaction.toBankAccount}</div>
                                <div className="col">{transaction.transactionType}</div>
                                <div className="col">{transaction.amount}</div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    )
}

export default Transactions
