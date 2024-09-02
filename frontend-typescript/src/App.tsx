import Accounts from "./Accounts.tsx";
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import Transactions from "./Transactions.tsx";

function App() {
  return (
      <Router>
        <Routes>
          <Route
              path="/"
              element={<Navigate to="/accounts"/>}/>
          <Route
              path="/accounts"
              element={<Accounts />} />
          <Route path="/accounts/:iban/transactions"
                 element={<Transactions />} />
        </Routes>
      </Router>
  )
}

export default App
