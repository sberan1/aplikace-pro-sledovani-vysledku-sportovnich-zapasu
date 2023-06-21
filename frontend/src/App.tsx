import React, {ReactNode} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import Match from './components/Match/Match';
import sparta from './assets/sparta.png';
import slavia from './assets/slavia.png';
import testFlag from './assets/czechRepublicFlag.svg';
import LeagueList from './components/LeagueList';
import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
import {UserProvider} from "./components/PrihlaseniPagePackage/UserContext";
import RegistracePage from './components/RegistracePagePackage/RegistracePage';
import Modal from 'react-modal';
import League from "./components/League/League";
import MatchList from "./components/MatchList";
import {MatchType} from "./components/Types";
import {MatchSourceType} from "./components/Enums";
import ContentHolder from "./components/ContentHolder/ContentHolder";
import BrowsingPage from "./components/BrowsingPagePackage/BrowsingPage";

Modal.setAppElement('#root');

const App = () => {
    return (
      <UserProvider>
    <Router>
        <Routes>
            <Route path="/" element={<HomePage HomePage="" PrihlaseniPage="" RegistracePage=""/>}/>
            <Route path="/prihlaseni" element={<PrihlaseniPage PrihlaseniPage="" RegistracePage=""/>}/>
            <Route path="/registrace" element={<RegistracePage  PrihlaseniPage="" RegistracePage=""/>} />
            <Route path="/basketbal" element={<BrowsingPage Sport={"Basketbal"} />}></Route>
            <Route path="/fotbal" element={<BrowsingPage Sport={"Fotbal"} />}></Route>
            <Route path="/hokej" element={<BrowsingPage Sport={"Hokej"} />}></Route>
            <Route path="/volejbal" element={<BrowsingPage Sport={"Volejbal"} />}></Route>
        </Routes>
    </Router>
   </UserProvider>
   );



    //return <ContentHolder sport={"Basketball"}/>;
}

export default App;
