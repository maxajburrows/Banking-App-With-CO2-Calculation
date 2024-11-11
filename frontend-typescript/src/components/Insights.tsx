import axios from "axios";

import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {InsightsResponse} from "../types/InsightsResponse.ts";
import Pie2 from "./Pie2.tsx";


function Insights() {
    const [insights, setInsights] = useState<InsightsResponse>({totalSpend: 0, categorySpend: new Map(), totalCo2: 0, categoryCo2: new Map()});
    const baseUrl: string = "http://localhost:8080/insights/";
    const requestHeaders: object = {"Authorization": `Bearer ${localStorage.getItem("token")}`};
    const {iban} = useParams();

    async function fetchInsights() {
        try {
            const response: InsightsResponse = (await axios.get(`${baseUrl}${iban}`, {headers: requestHeaders})).data;
            setInsights(response)
            console.log(response);
        } catch (e) {
            console.error(e);
        }
    }

    useEffect(() => {
        fetchInsights();
    }, []);

    return (
        <>
            <section className="background-radial-gradient min-vh-100 justify-content-center">
                <div className="container px-4 px-md-5 text-center text-lg-start">
                    <div className="row gx-lg-5 align-items-center">
                        <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>Insights</h1>
                        <h3 className="mb-4" style={{color: 'hsl(218, 81%, 95%)'}}>Your total expenditure in the last year was
                            € {insights.totalSpend.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}.</h3>
                        <div className="card blur-bg mb-4">
                            <div className="card-body blur-bg" >
                                <Pie2 data={insights.categorySpend} title="Yealy spend by Category"/>
                            </div>
                        </div>
                        <h3 className="mb-4" style={{color: 'hsl(218, 81%, 95%)'}}>Your total CO2 emissions in the last year
                            were {insights.totalCo2.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')} kg.</h3>
                        <div className="card blur-bg mb-4">
                            <div className="card-body blur-bg">
                                <Pie2 data={insights.categorySpend} title="Yealy CO2 emissions by Category"/>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Insights
