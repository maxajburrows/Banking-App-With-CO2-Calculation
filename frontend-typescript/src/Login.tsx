import axios from 'axios';

import { useNavigate } from "react-router-dom";
import React, {useState} from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [failedLogin, setFailedLogin] = useState(false);
    const navigate = useNavigate();

    async function handleLogin(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const requestUrl : string = "http://localhost:8080/auth/login";
        const requestBody : object = { username, password };
        try {
            const token = (await axios.post(requestUrl, requestBody)).data;
            navigate("/accounts");
            localStorage.setItem("token", token);
            console.log(token);
        } catch (e) {
            console.error(e);
            setFailedLogin(true);
        }
    }

    return (<>
        {failedLogin && <p>Incorrect username or password</p>}
        <form onSubmit={handleLogin}>
            <label htmlFor="username">Username</label>
            <input type="text" id="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
            <label htmlFor="password">Password</label>
            <input type="text" id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <button type="submit">Login</button>
        </form>
    </>);
}

export default Login;
