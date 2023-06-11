import React, {ReactNode} from 'react';
// @ts-ignore
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // import 'Routes' instead of 'Switch'
import './App.css';
import Match from './components/match/Match';
import LeagueList from './components/LeagueList';

import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
//import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
//import RegistracePage from './components/RegistracePagePackage/RegistracePage';

interface AppProps {
    HomePage: ReactNode,
    PrihlaseniPage: ReactNode,
    RegistracePage: ReactNode,
    navbarPrihlaseniPage: ReactNode,
    navbarRegistracePage: ReactNode
}

const App = ({ HomePage, navbarPrihlaseniPage, navbarRegistracePage }: AppProps) => {
    // @ts-ignore
    return (
        <Router>
            <Navbar HomePage={HomePage} PrihlaseniPage={navbarPrihlaseniPage} RegistracePage={navbarRegistracePage}/>
            <Routes>
                <Route path="/" element={HomePage} />
                {/* <Route path="/prihlaseni" element={<PrihlaseniPage />} />
            <Route path="/registrace" element={<RegistracePage />} />*/}
            </Routes>
        </Router>
    );
};

export default App;
