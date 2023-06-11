import React, {ReactNode} from 'react';
// @ts-ignore
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // import 'Routes' instead of 'Switch'
import './App.css';
import Match from './components/match/Match';
import sparta from './assets/sparta.png';
import slavia from './assets/slavia.png';
import LeagueList from './components/LeagueList';
import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
//import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
//import RegistracePage from './components/RegistracePagePackage/RegistracePage';

const App = () => {
    // @ts-ignore
    // return (
    //     <Router>
    //         <Routes>
    //             <Route path="/" element={<HomePage  HomePage="" PrihlaseniPage="" RegistracePage=""/>} />
    //             {/* <Route path="/prihlaseni" element={<PrihlaseniPage />} />
    //             <Route path="/registrace" element={<RegistracePage />} />*/}
    //         </Routes>
    //     </Router>
    // );

    return (
        <Match
            date="12.2.2020"
            time="13:14"
            team1="Sparta"
            team2="Slavia"
            score1={1}
            score2={0}
            imgSource1={sparta}
            imgSource2={slavia}
        />
    )
};

export default App;
