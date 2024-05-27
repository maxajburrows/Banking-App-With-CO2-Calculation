import { useState } from "react";


function SendMoney({ handleSent }) {
    const [iban, setIban] = useState('');
    const [amount, setAmount] = useState('');
    const [description, setDescription] = useState('');

    function handleSubmit(e) {
        e.preventDefault();
        const formData = {
            iban,
            amount,
            description
        }
        setIban('');
        setDescription('');
        setAmount('');
        handleSent(formData);
    }

    return (
        <section>
            <h3>Transfer Money</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Iban:</label>
                    <input
                        type="text"
                        value={iban}
                        onChange={(e) => setIban(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Amount:</label>
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Description:</label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Send</button>
            </form>
        </section>
    )
}

export default SendMoney;