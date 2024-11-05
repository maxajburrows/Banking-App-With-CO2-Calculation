import axios from 'axios';
import './style/general.css';

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

    <section className="background-radial-gradient overflow-hidden vh-100 justify-content-center">
        <div className="container px-4 px-md-5 text-center text-lg-start my-5">
            <div className="row gx-lg-5 align-items-center mb-5">
                <div className="col-lg-6 mb-5 mb-lg-0" style={{zIndex: 10}}>
                    <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>
                        Max <br />
                        <span style={{color: 'hsl(218, 81%, 75%)'}}>Banking</span>
                    </h1>
                    <p className="mb-4 opacity-70" style={{color: 'hsl(218, 81%, 85%)'}}>
                        Maximum security. Maximum returns. Maximum value.
                    </p>
                </div>

                <div className="col-lg-6 mb-5 mb-lg-0 position-relative">
                    <div id="radius-shape-1" className="position-absolute rounded-circle shadow-5-strong"></div>
                    <div id="radius-shape-2" className="position-absolute shadow-5-strong"></div>

                    <div className="card bg-glass">
                        <div className="card-body px-4 py-5 px-md-5">
                            <form onSubmit={handleLogin}>
                                {failedLogin && <p>Incorrect username or password</p>}

                                <div data-mdb-input-init className="form-outline mb-4">
                                    <label className="form-label" htmlFor="form3Example3">Email address</label>
                                    <input type="email" id="form3Example3" className="form-control" placeholder="Enter your username" value={username}
                                           onChange={e => setUsername(e.target.value)}/>
                                </div>

                                <div data-mdb-input-init className="form-outline mb-4">
                                <label className="form-label" htmlFor="form3Example4">Password</label>
                                    <input type="password" id="form3Example4" className="form-control" placeholder="Enter your password" value={password}
                                           onChange={e => setPassword(e.target.value)}/>
                                </div>
                                <button type="submit" data-mdb-button-init data-mdb-ripple-init className="btn btn-primary btn-block">
                                    Login
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </>)
}

export default Login;
