import React, {ReactNode} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import Match from './components/Match/Match';
import sparta from './assets/sparta.png';
import slavia from './assets/slavia.png';
import LeagueList from './components/LeagueList';
import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
import {UserProvider} from "./components/PrihlaseniPagePackage/UserContext";
import RegistracePage from './components/RegistracePagePackage/RegistracePage';
//import FotbalPage from './components/ZkusebniPageFotbalu/FotbalPage';
import Modal from 'react-modal';
import League from "./components/League/League";
import MatchList from "./components/MatchList";
import {MatchType} from "./components/Types";
import {MatchSourceType} from "./components/Enums";

Modal.setAppElement('#root');

const App = () => {
    /*return (
        <UserProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<HomePage HomePage="" PrihlaseniPage="" RegistracePage=""/>}/>
                    <Route path="/prihlaseni" element={<PrihlaseniPage PrihlaseniPage="" RegistracePage=""/>}/>
                    <Route path="/registrace" element={<RegistracePage  PrihlaseniPage="" RegistracePage=""/>} />
                    <Route path="/fotbal" element={<FotbalPage />}></Route>
                </Routes>
            </Router>
        </UserProvider>
    );*/

    const LocalMatchList : Array<JSX.Element> = MatchList(1, "?sport=Basketball&date=2023-03-19&league=1130");

    return <League id={MatchSourceType.League} name={"Testovací liga"} flagSource={sparta} matchList={LocalMatchList} />
};

export default App;
