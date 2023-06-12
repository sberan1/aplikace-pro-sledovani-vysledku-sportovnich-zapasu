import React, {ReactNode} from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Match from './components/match/Match';
import LeagueList from './components/LeagueList';
import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
//import RegistracePage from './components/RegistracePagePackage/RegistracePage';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage  HomePage="" PrihlaseniPage="" RegistracePage=""/>} />
                <Route path="/prihlaseni" element={<PrihlaseniPage  PrihlaseniPage="" RegistracePage=""/>} />
               {/*  <Route path="/registrace" element={<RegistracePage />} />*/}
            </Routes>
        </Router>
    );
};

export default App;
