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
import League from "./components/league/League";
import MatchList from "./components/MatchList";
//import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
//import RegistracePage from './components/RegistracePagePackage/RegistracePage';
import Type from "./components/MatchList";

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

    let matchListT: any[] = [

    ];

    return (
        <League id={1} name={"Testovací liga"} matchList={MatchList(1, '?sport=Basketball&date=2023-05-15&league=1130')}></League>
    )
};

export default App;
