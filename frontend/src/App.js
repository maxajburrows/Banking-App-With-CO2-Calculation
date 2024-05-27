import './App.css';
import { useEffect, useState } from 'react';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    async function fetchData() {
      let response = await fetch("http://localhost:8080/accounts");
      if (!response.ok) {
        throw Error("Can't get accounts")
      }
      setData(await response.json());
    }
    fetchData();
  }, []);



  return (
    <div className="App">
      {data.map((account) =>
        <>
          <p>{account.name}</p>
          <p>{account.iban}</p>
        </>)}
    </div>
  );
}

export default App;
