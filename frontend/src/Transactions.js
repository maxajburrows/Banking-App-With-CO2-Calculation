import { useParams } from "react-router-dom"
import { useEffect, useState } from 'react';
import './App.css';



function Transactions() {
    const { iban } = useParams();
    const [transationData, setTransactionData] = useState([]);

    async function fetchData() {
        let sentMoney = await fetch(`http://localhost:8080/transactions?from_iban=${iban}`);
        let receivedMoney = await fetch(`http://localhost:8080/transactions?to_iban=${iban}`);

        if (!sentMoney.ok || !receivedMoney.ok) {
            throw Error("Can't get accounts")
        }
        setTransactionData([...(await sentMoney.json()), ...(await receivedMoney.json())]);
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
                    <th>Sent/Received</th>
                </tr>
                {transationData.map((transaction) => (
                    <tr>
                        <td>{transaction.datetime}</td>
                        <td>{transaction.category}</td>
                        <td>{transaction.amount}</td>
                        {transaction.from_iban === iban ? "Sent" : "Received"}
                    </tr>
                ))}
                </table>
        </section>
    )
}

export default Transactions