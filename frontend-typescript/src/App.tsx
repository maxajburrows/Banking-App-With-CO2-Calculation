import Accounts from "./Accounts.tsx";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Transactions from "./Transactions.tsx";
import Login from "./Login.tsx";

function App() {
  return (
      <Router>
        <Routes>
          <Route
              path="/"
              element={<Login />} />
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
