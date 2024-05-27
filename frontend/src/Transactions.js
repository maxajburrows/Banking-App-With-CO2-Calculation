import { useParams } from "react-router-dom"
import { useEffect, useState } from 'react';
import './App.css';



function Transactions() {
    const { iban } = useParams();
    const [transationData, setTransactionData] = useState([]);

    async function fetchData() {
        let response = await fetch(`http://localhost:8080/transactions?from_iban=${iban}`);
        if (!response.ok) {
            throw Error("Can't get accounts")
        }
        setTransactionData(await response.json());
    }

    useEffect(() => {
        fetchData();
    }, []);

    return (
        <section>
            <h1>Transactions</h1>

            <table>
                <tr>
                    <th>Time</th>
                    <th>Category</th>
                    <th>Amount</th>
                </tr>
                {transationData.map((transaction) => (
                    <tr>
                        <td>{transaction.datetime}</td>
                        <td>{transaction.category}</td>
                        <td>{transaction.amount}</td>
                    </tr>
                ))}
                </table>
        </section>
    )
}

export default Transactions