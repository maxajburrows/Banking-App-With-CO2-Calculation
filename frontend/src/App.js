import Accounts from './Accounts';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Transactions from './Transactions';


function App() {
  return (
    <Router>
      <Routes>
        <Route
          path='/'
          element={<Navigate to='/accounts' />}>
        </Route>
        <Route
          path='/accounts'
          element={<Accounts />}>
        </Route>
        <Route
          path='/accounts/:iban/transactions'
          element={<Transactions />}>
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
