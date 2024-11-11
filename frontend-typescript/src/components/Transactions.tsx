import axios from "axios";

import {useParams} from "react-router-dom";
import {Transaction} from "../types/Transaction.ts";
import {useEffect, useState} from "react";
import {format} from "date-fns";


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

    const groupedTransactions = transactions.reduce((acc, transaction) => {
        const date = format(new Date(transaction.transactionDateTime.substring(0,10)), 'yyyy-MM-dd');
        if (!acc[date]) {
            acc[date] = [];
        }
        acc[date].push(transaction);
        return acc;
    }, {} as { [key: string]: Transaction[] });

    return (
        <section className="background-radial-gradient min-vh-100 justify-content-center">
            <div className="container px-4 px-md-5 text-center text-lg-start">
                <div className="row gx-lg-5 align-items-center mb-5">
                    <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>Transactions</h1>
                    <div className="container">
                        <div className="row align-self-center mb-2">
                            <div className="col-6 fw-bold" style={{color: 'hsl(218, 81%, 85%)'}}>Recipient</div>
                            <div className="col-3 fw-bold" style={{color: 'hsl(218, 81%, 85%)'}}>Category</div>
                            <div className="col-3 fw-bold" style={{color: 'hsl(218, 81%, 85%)'}}>Amount</div>
                        </div>
                    </div>
                    {Object.keys(groupedTransactions).map(date => (
                        <div key={date}>
                            <p className="my-3" style={{color: 'hsl(218, 81%, 85%)'}}>{date}</p>
                            {groupedTransactions[date].map(transaction => (
                                <div className="card blur-bg mt-2">
                                    <div className="card-body blur-bg row transaction-card">
                                        <div className="col-6 transaction-text" style={{color: 'hsl(218, 81%, 85%)'}}>{transaction.toBankAccount}</div>
                                        <div className="col-3 transaction-text" style={{color: 'hsl(218, 81%, 85%)'}}>{transaction.category.categoryName}</div>
                                        {transaction.transactionType === "SENT" &&
                                            <div className="col-3 align-self-end transaction-text fw-bold"
                                                 style={{color: 'red'}}>-€{transaction.amount.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}</div>}
                                        {transaction.transactionType === "RECEIVED" &&
                                            <div className="col-3 align-self-end transaction-text fw-bold"
                                                 style={{color: 'green'}}>+€{transaction.amount.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}</div>}
                                    </div>
                                </div>
                            ))}
                        </div>
                    ))}
                </div>
            </div>
        </section>
    )
}

export default Transactions
