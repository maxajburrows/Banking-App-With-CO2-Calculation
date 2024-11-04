import axios from "axios";
import {useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {User} from "./types/User.ts";
import {Account} from "./types/Account.ts";

function SendMoney() {
    const requestHeaders: object = {"Authorization": `Bearer ${localStorage.getItem("token")}`};
    const username: string | null = localStorage.getItem("username");
    const navigate = useNavigate();

    const [accounts, setAccounts] = useState<Account[]>([]);
    const [receiverIban, setReceiverIban] = useState("");
    const [userIban, setUserIban] = useState("");
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");

    async function sendMoneyRequest(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const requestUrl: string = "http://localhost:8080/transactions";
        const requestBody: object = {
            fromBankAccount: username,
            toBankAccount: receiverIban,
            amount,
            description
        }
        console.log(requestHeaders)
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
            <form onSubmit={sendMoneyRequest}>
                <label htmlFor="UserIban">Your Iban</label>
                <select id="UserIban" value={userIban} onChange={e => setUserIban(e.target.value)}>
                    <option value="">Select your account</option>
                    {accounts.map((account) => (
                        <option value={account.iban}>{account.accountName}</option>
                    ))}
                </select>
                <label htmlFor="ReceiverIban">Iban</label>
                <input type="text" id="ReceiverIban" value={receiverIban} onChange={e => setReceiverIban(e.target.value)}/>
                <label htmlFor="amount">Amount</label>
                <input type="number" id="amount" value={amount} onChange={e => setAmount(e.target.value)}/>
                <label htmlFor="description">Description</label>
                <input type="text" id="description" value={description} onChange={e => setDescription(e.target.value)}/>
                <button type="submit">Send Money</button>
            </form>
        </>);
}

export default SendMoney;
