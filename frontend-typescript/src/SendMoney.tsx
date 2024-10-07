import axios from "axios";
import {useNavigate} from "react-router-dom";

function SendMoney() {
    const requestUrl : string = "http://localhost:8080/transactions/";
    const username : string | null = localStorage.getItem("username");
    const password : string | null = localStorage.getItem("password");
    const encodedCredentials : string = btoa(`${username}:${password}`);
    const requestHeaders : object = { "Authorization": `Basic ${encodedCredentials}` };
    const navigate = useNavigate();

    async function sendMoneyRequest(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        try {
            await axios.post(requestUrl, { headers: requestHeaders})
        }
        catch (e) {
            console.error(e);
        }
        navigate("/accounts");  // TODO: Successful transaction page?
    }

    return (<>
        <form onSubmit={sendMoneyRequest}>
            <label htmlFor="Iban">Iban</label>
            <input type="text" id="Iban"/>
            <label htmlFor="amount">Amount</label>
            <input type="number" id="amount"/>
            <label htmlFor="description">Description</label>
            <input type="text" id="description"/>
            <button type="submit">Send Money</button>
        </form>
    </>);
}

export default SendMoney;
