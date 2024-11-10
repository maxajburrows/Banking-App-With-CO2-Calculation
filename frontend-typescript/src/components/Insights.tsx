import axios from "axios";

import {useParams} from "react-router-dom";
// import {Transaction} from "../types/Transaction.ts";
import {useEffect, useState} from "react";
import {InsightsResponse} from "../types/InsightsResponse.ts";


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
            <section className="background-radial-gradient overflow-hidden vh-100 justify-content-center">
                <div className="container px-4 px-md-5 text-center text-lg-start my-5">
                    <div className="row gx-lg-5 align-items-center mb-5">
                        <div>
                            <h1 className="my-5 display-5 fw-bold ls-tight" style={{color: 'hsl(218, 81%, 95%)'}}>Insights</h1>
                            <p>{insights.totalSpend}</p>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Insights
