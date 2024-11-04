import axios from "axios";
import {useNavigate} from "react-router-dom";
import React, {useState} from "react";

function SendMoney() {
    const requestUrl : string = "http://localhost:8080/transactions";
    const requestHeaders : object = { "Authorization": `Bearer ${localStorage.getItem("token")}` };
    const username : string | null = localStorage.getItem("username");
    const navigate = useNavigate();

    const [iban, setIban] = useState("");
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");



    async function sendMoneyRequest(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const requestBody : object = {
            fromBankAccount: username,
            toBankAccount: iban,
            amount,
            description
        }
        try {
            await axios.post(requestUrl, requestBody, { headers: requestHeaders })
        }
        catch (e) {
            console.error(e);
        }
        navigate("/accounts");  // TODO: Successful transaction page?
    }

    return (<>
        <form onSubmit={sendMoneyRequest}>
            <label htmlFor="Iban">Iban</label>
            <input type="text" id="Iban" value={iban} onChange={e => setIban(e.target.value)}/>
            <label htmlFor="amount">Amount</label>
            <input type="number" id="amount" value={amount} onChange={e => setAmount(e.target.value)}/>
            <label htmlFor="description">Description</label>
            <input type="text" id="description" value={description} onChange={e => setDescription(e.target.value)}/>
            <button type="submit">Send Money</button>
        </form>
    </>);
}

export default SendMoney;
