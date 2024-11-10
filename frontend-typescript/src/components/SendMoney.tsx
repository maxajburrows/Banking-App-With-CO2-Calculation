import axios from "axios";
import {useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {User} from "../types/User.ts";
import {Account} from "../types/Account.ts";

function SendMoney() {
    const requestHeaders: object = {"Authorization": `Bearer ${localStorage.getItem("token")}`};
    const username: string | null = localStorage.getItem("username");
    const navigate = useNavigate();

    const [accounts, setAccounts] = useState<Account[]>([]);
    const [receiverIban, setReceiverIban] = useState("");
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");

    async function sendMoneyRequest(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const requestUrl: string = "http://localhost:8080/transactions";
        const requestBody: object = {
            transactionOwnerUsername: username,
            toBankAccount: receiverIban,
            amount,
            description
        }
        try {
            const response = (await axios.post(requestUrl, requestBody, {headers: requestHeaders})).data
            console.log(response)
        } catch (e) {
            console.error(e);
        }
        navigate("/accounts");  // TODO: Successful transaction page?

    }

    async function fetchAccountData() {
        const baseUrl: string = "http://localhost:8080/users/";
        try {
            const response: User = (await axios.get(`${baseUrl}${username}`, {headers: requestHeaders})).data;
            setAccounts(response.bankAccounts);
            console.log(response);
        } catch (e) {
            console.error(e);
        }
    }

    useEffect(() => {
        fetchAccountData();
    }, []);

    return (<>
            <section className="background-radial-gradient overflow-hidden vh-100 justify-content-center">
                <div className="container px-4 px-md-5 text-center text-lg-start my-5">
                    <div className="row gx-lg-5 align-items-center mb-5">
                        <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>
                            Transfer Money
                        </h1>
                        <div className="col-lg-6 mb-5 mb-lg-0 position-relative">
                            <div id="radius-shape-1" className="position-absolute rounded-circle shadow-5-strong"></div>
                            <div id="radius-shape-2" className="position-absolute shadow-5-strong"></div>
                            <div className="card bg-glass">
                                <div className="card-body bg-glass px-4 py-5 px-md-5">
                                    <form onSubmit={sendMoneyRequest}>
                                        <div data-mdb-input-init className="form-outline mb-4">

                                            <select className='form-select'>
                                                <option selected>Select your account</option>
                                                {accounts.map((account) => (
                                                    <option value={account.iban}>{account.accountName}</option>
                                                ))}
                                            </select>
                                        </div>
                                        <div data-mdb-input-init className="form-outline mb-4">
                                            <label className="form-label" htmlFor="ReceiverIban">Iban</label>
                                            <input type="text" id="ReceiverIban" className="form-control" placeholder="Enter receipients Iban" value={receiverIban}
                                                   onChange={e => setReceiverIban(e.target.value)}/>
                                        </div>
                                        <div data-mdb-input-init className="form-outline mb-4">
                                            <label className="form-label" htmlFor="amount">Amount</label>
                                            <input className="form-control" type="number" id="amount" placeholder="Enter transfer amount" value={amount}
                                                   onChange={e => setAmount(e.target.value)}/>
                                        </div>
                                        <div data-mdb-input-init className="form-outline mb-4">
                                            <label className="form-label" htmlFor="description">Description</label>
                                            <input className="form-control" type="text" id="description" placeholder="Enter description" value={description}
                                                   onChange={e => setDescription(e.target.value)}/>
                                        </div>
                                        <button type="submit" data-mdb-button-init data-mdb-ripple-init className="btn btn-primary btn-block">Send Money</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default SendMoney;
