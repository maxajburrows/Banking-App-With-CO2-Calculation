import axios from "axios";
import {useNavigate} from "react-router-dom";
import React, {useState} from "react";

function SendMoney() {
    const requestUrl : string = "http://localhost:8080/transactions";
    const requestHeaders : object = { "Authorization": `Bearer ${localStorage.getItem("token")}` };
    const username : string | null = localStorage.getItem("username");
    const navigate = useNavigate();

    const [receiverIban, setReceiverIban] = useState("");
    const [userIban, setUserIban] = useState("");
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");

    async function sendMoneyRequest(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const requestBody : object = {
            fromBankAccount: username,
            toBankAccount: receiverIban,
            amount,
            description
        }
        console.log(requestHeaders)
        try {
            const response = (await axios.post(requestUrl, requestBody, { headers: requestHeaders })).data
            console.log(response)
        }
        catch (e) {
            console.error(e);
        }
        navigate("/accounts");  // TODO: Successful transaction page?
    }

    // TODO: Call backend to get user's accounts

    return (<>
        <form onSubmit={sendMoneyRequest}>
            <label htmlFor={"UserIban"}>Your Iban</label>
            <input type="text" id="UserIban" value={userIban} onChange={e => setUserIban(e.target.value)}/>
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
