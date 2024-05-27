import './App.css';
import { useEffect, useState } from 'react';

function App() {
  const [data, setData] = useState([]);

  async function fetchData() {
    let response = await fetch("http://localhost:8080/accounts");
    if (!response.ok) {
      throw Error("Can't get accounts")
    }
    setData(await response.json());
  }

  useEffect(() => {
    fetchData();
  }, []);





  return (
    <div className="App">
      {data.map((account) =>
        <>
          <button>
            <h2>{account.name}</h2>
            <p>{account.iban}</p>
          </button>
        </>)}
    </div>
  );
}

export default App;
