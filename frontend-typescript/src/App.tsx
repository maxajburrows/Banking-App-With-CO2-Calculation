import Homepage from "./components/Homepage.tsx";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Transactions from "./components/Transactions.tsx";
import Login from "./components/Login.tsx";
import SendMoney from "./components/SendMoney.tsx";
import Insights from "./components/Insights.tsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={<Login/>}/>
                <Route
                    path="/accounts"
                    element={<Homepage/>}/>
                <Route path="/accounts/:iban/transactions"
                       element={<Transactions/>}/>
                <Route path="/accounts/:iban/insights"
                       element={<Insights/>}/>
                <Route path="/accounts/:user/transfer"
                       element={<SendMoney/>}/>
            </Routes>
        </Router>
    )
}

export default App
