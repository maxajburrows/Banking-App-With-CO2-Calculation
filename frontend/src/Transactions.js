import SendMoney from "./SendMoney.js";
import { useParams } from "react-router-dom"
import { useEffect, useState } from 'react';
import './App.css';




function Transactions() {
    const { iban } = useParams();
    const [transationData, setTransactionData] = useState([]);
    const [refresh, setRefresh] = useState(false);


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
    }, [iban, refresh]);

    async function handleSubmit(formData) {
        try {
            const response = await fetch('http://localhost:8080/transactions', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    from_iban: iban,
                    to_iban: formData.iban,
                    datetime: new Date().toISOString(),
                    amount: formData.amount,
                    currency: 'EUR',
                    description: formData.description,
                    category: "Other"
                })
            })
            if (!response.ok) {
                throw new Error("Response was not ok")
            }
            const result = await response.json();
            console.log(result);
            setRefresh(prev => !prev)
            console.log(refresh)
        } catch (error) {
            console.log("Error:", error)
        }
        
        


    }

    return (
        <>
            <SendMoney handleSent={handleSubmit} />
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
        </>
    )
}

export default Transactions