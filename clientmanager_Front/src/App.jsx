import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Clientes from './pages/Clientes';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/clientes" element={<Clientes />} />
      </Routes>
    </Router>
  );
}

export default App;
