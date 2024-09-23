import axios from 'axios';

import { useNavigate } from "react-router-dom";
import React, {useState} from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    async function handleLogin(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const encodedCredentials : string = btoa(`${username}:${password}`);
        const requestHeaders : object = { "Authorization": `Basic ${encodedCredentials}` };
        try {
            await axios.get("http://localhost:8080/accounts", { headers: requestHeaders });
            navigate("/accounts");
            localStorage.setItem("username", username);
            localStorage.setItem("password", password);
            const retrievedUsername =  localStorage.getItem("username");
            console.log(retrievedUsername)
            console.log("Successfully logged in");
        } catch (e) {
            console.error(e);
            console.log("Incorrect username or password");
        }
    }

    return (
        <form onSubmit={handleLogin}>
            <label htmlFor="username">Username</label>
            <input type="text" id="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
            <label htmlFor="password">Password</label>
            <input type="text" id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <button type="submit">LOG IN</button>
        </form>
    );
}

export default Login;
