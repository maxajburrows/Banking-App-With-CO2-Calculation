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

    // return (<>
    //     {failedLogin && <p>Incorrect username or password</p>}
    //     <div className="container vh-100">
    //         <div className="row justify-content-center align-content-center vh-100">
    //             <div className="col-md-7 text-center">
    //                 <div className="text-center">
    //                     <h1>Basic Bank</h1>
    //                     <p className="col-8 mx-auto">No thrills banking. We keep your money safe. That's it.</p>
    //                     <h3>Login</h3>
    //                     <form onSubmit={handleLogin}>
    //                         <div className="form-group">
    //                             <label className="col" htmlFor="username">Username</label>
    //                             <input type="text" id="username" className="form-control" placeholder="Enter username" value={username}
    //                                    onChange={e => setUsername(e.target.value)}/>
    //                         </div>
    //                         <div className="form-group">
    //                             <label className="text-" htmlFor="password">Password</label>
    //                             <input type="password" id="password" className="form-control" placeholder="Enter password" value={password}
    //                                    onChange={e => setPassword(e.target.value)}/>
    //                         </div>
    //                         <button type="submit" className="btn btn-primary">Login</button>
    //                     </form>
    //                 </div>
    //             </div>
    //         </div>
    //     </div>
    // </>);

    return (<>

    <section className="background-radial-gradient overflow-hidden">
        <div className="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
            <div className="row gx-lg-5 align-items-center mb-5">
                <div className="col-lg-6 mb-5 mb-lg-0" style={{zIndex: 10}}>
                    <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>
                        The best offer <br />
                        <span style={{color: 'hsl(218, 81%, 75%)'}}>for your business</span>
                    </h1>
                    <p className="mb-4 opacity-70" style={{color: 'hsl(218, 81%, 85%)'}}>
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                        Temporibus, expedita iusto veniam atque, magni tempora mollitia
                        dolorum consequatur nulla, neque debitis eos reprehenderit quasi
                        ab ipsum nisi dolorem modi. Quos?
                    </p>
                </div>

                <div className="col-lg-6 mb-5 mb-lg-0 position-relative">
                    <div id="radius-shape-1" className="position-absolute rounded-circle shadow-5-strong"></div>
                    <div id="radius-shape-2" className="position-absolute shadow-5-strong"></div>

                    <div className="card bg-glass">
                        <div className="card-body px-4 py-5 px-md-5">
                            <form>
                                <div className="row">
                                    <div className="col-md-6 mb-4">
                                        <div data-mdb-input-init className="form-outline">
                                            <input type="text" id="form3Example1" className="form-control" />
                                            <label className="form-label" htmlFor="form3Example1">First name</label>
                                        </div>
                                    </div>
                                    <div className="col-md-6 mb-4">
                                        <div data-mdb-input-init className="form-outline">
                                            <input type="text" id="form3Example2" className="form-control" />
                                            <label className="form-label" htmlFor="form3Example2">Last name</label>
                                        </div>
                                    </div>
                                </div>

                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input type="email" id="form3Example3" className="form-control" />
                                    <label className="form-label" htmlFor="form3Example3">Email address</label>
                                </div>

                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input type="password" id="form3Example4" className="form-control" />
                                    <label className="form-label" htmlFor="form3Example4">Password</label>
                                </div>

                                <div className="form-check d-flex justify-content-center mb-4">
                                    <input className="form-check-input me-2" type="checkbox" value="" id="form2Example33" checked />
                                    <label className="form-check-label" htmlFor="form2Example33">
                                        Subscribe to our newsletter
                                    </label>
                                </div>

                                <button type="submit" data-mdb-button-init data-mdb-ripple-init className="btn btn-primary btn-block mb-4">
                                    Sign up
                                </button>

                                <div className="text-center">
                                    <p>or sign up with:</p>
                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init className="btn btn-link btn-floating mx-1">
                                        <i className="fab fa-facebook-f"></i>
                                    </button>

                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init className="btn btn-link btn-floating mx-1">
                                        <i className="fab fa-google"></i>
                                    </button>

                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init className="btn btn-link btn-floating mx-1">
                                        <i className="fab fa-twitter"></i>
                                    </button>

                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init className="btn btn-link btn-floating mx-1">
                                        <i className="fab fa-github"></i>
                                    </button>
                                </div>
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
