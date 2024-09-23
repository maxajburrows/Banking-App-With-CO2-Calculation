// import axios from 'axios';

import React, {useState} from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
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
