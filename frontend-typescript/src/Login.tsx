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
            localStorage.setItem("username", username);
            localStorage.setItem("token", token);
            console.log(token);
        } catch (e) {
            console.error(e);
            setFailedLogin(true);
        }
    }

    return (<>
        {failedLogin && <p>Incorrect username or password</p>}
        <div className="container vh-100">
            <div className="row justify-content-center align-content-center vh-100">
                <div className="col-md-7 text-center">
                    <div className="text-center">
                        <h1>Basic Bank</h1>
                        <p className="col-8 mx-auto">No thrills banking. We keep your money safe. That's it.</p>
                        <h3>Login</h3>
                        <form onSubmit={handleLogin}>
                            <div className="form-group">
                                <label className="col" htmlFor="username">Username</label>
                                <input type="text" id="username" className="form-control" placeholder="Enter username" value={username}
                                       onChange={e => setUsername(e.target.value)}/>
                            </div>
                            <div className="form-group">
                                <label className="text-" htmlFor="password">Password</label>
                                <input type="password" id="password" className="form-control" placeholder="Enter password" value={password}
                                       onChange={e => setPassword(e.target.value)}/>
                            </div>
                            <button type="submit" className="btn btn-primary">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </>);
}

export default Login;
