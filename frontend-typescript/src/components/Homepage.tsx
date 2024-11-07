import axios from "axios";
import '../style/general.css';

import {useEffect, useState} from "react";
import {Account} from "../types/Account.ts";
import {User} from "../types/User.ts";
import {useNavigate} from "react-router-dom";


function Homepage() {
    const [usersName, setUsersName] = useState<string>("");
    const [accounts, setAccounts] = useState<Account[]>([]);
    const baseUrl: string = "http://localhost:8080/users/";
    const username: string | null = localStorage.getItem("username");
    const requestHeaders: object = {"Authorization": `Bearer ${localStorage.getItem("token")}`};
    const navigate = useNavigate();


    async function fetchAccountData() {
        try {
            const response: User = (await axios.get(`${baseUrl}${username}`, {headers: requestHeaders})).data;
            setUsersName(response.firstName)
            setAccounts(response.bankAccounts);
            console.log(response);
        } catch (e) {
            console.error(e);
        }
    }

    function goToAccount(iban: string) {
        navigate(`/accounts/${iban}/transactions`);
    }


    useEffect(() => {
        fetchAccountData();
    }, []);

    return (
        <>
        <section className="background-radial-gradient overflow-hidden vh-100 justify-content-center">
            <div className="container px-4 px-md-5 text-center text-lg-start my-5">
                <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>Welcome back {usersName}</h1>
                <div className="row gx-lg-5 align-items-center mb-5">
                        <button className="col btn btn-outline-light" style={{height: '100%'}} onClick={() => navigate(`/accounts/${username}/transfer`)}>
                            <h2>Transfer money</h2>
                        </button>

                        <button className="col btn btn-outline-light" style={{height: '100%'}} onClick={() => navigate(`/accounts/${accounts[1].iban}/insights`)}>
                            <h2>Insights</h2>
                        </button>

                </div>
                <div className="col-lg-6 mb-5 mb-lg-0 position-relative">
                    <div id="radius-shape-1" className="position-absolute rounded-circle shadow-5-strong"></div>
                    <div id="radius-shape-2" className="position-absolute shadow-5-strong"></div>

                    <h1>Your Accounts</h1>
                    {accounts.map((account) => (
                        <button type="button" className="btn btn-outline-dark btn-lg btn-block" onClick={() => goToAccount(account.iban)}>
                            <h2>{account.accountName}</h2>
                            <p>{account.iban}</p>
                            <p>€{account.balance.toFixed(2)}</p>
                        </button>
                    ))}
                </div>
            </div>

        </section>
</>
)
    ;
}

export default Homepage;
