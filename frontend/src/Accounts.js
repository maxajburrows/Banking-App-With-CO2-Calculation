import './App.css';
import { useEffect, useState } from 'react';


function Accounts() {
    const [data, setData] = useState([]);

    async function fetchData() {
        let response = await fetch("http://localhost:8080/accounts");
        if (!response.ok) {
            throw Error("Can't get accounts")
        }
        console.log("ran use effect")
        setData(await response.json());
    }

    useEffect(() => {
        fetchData();
    }, []);


    return (
        <section>
            <h1>My Accounts</h1>
            {data.map((account) => (
                <button key={account.id} type="button" className="btn btn-outline-dark btn-lg btn-block" >
                    <h2>{account.name}</h2>
                    <p>{account.iban}</p>
                </button>
            ))}
        </section>
    );
}

export default Accounts;