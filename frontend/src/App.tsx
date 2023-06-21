import React, {ReactNode, useContext} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import Match from './components/Match/Match';
import sparta from './assets/sparta.png';
import slavia from './assets/slavia.png';
import testFlag from './assets/czechRepublicFlag.svg';
import LeagueList from './components/LeagueList';
import Navbar from './pages/HomePagePackage/Navbar';
import HomePage from './pages/HomePagePackage/HomePage';
import PrihlaseniPage from './pages/PrihlaseniPagePackage/PrihlaseniPage';
import {UserContext, UserProvider} from "./pages/PrihlaseniPagePackage/UserContext";
import RegistracePage from './pages/RegistracePagePackage/RegistracePage';
import Modal from 'react-modal';
import League from "./components/League/League";
import MatchList from "./components/MatchList";
import {MatchType} from "./components/Types";
import {MatchSourceType} from "./components/Enums";
import ContentHolder from "./components/ContentHolder/ContentHolder";
import BrowsingPage from "./components/BrowsingPagePackage/BrowsingPage";
import Dashboard from './pages/DashboardPage/Dashboard';
import ProfilTymu from "./pages/ProfilTymuPage/ProfilTymu";


Modal.setAppElement('#root');

const App = () => {
    const { user } = useContext(UserContext);
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
            <Route path="/dashboard" element={<Dashboard  userId={user?.userId}/>}></Route>
            <Route path="/tym/:teamId" element={<ProfilTymu />}></Route>
        </Routes>
    </Router>
   </UserProvider>
   );



    //return <ContentHolder sport={"Basketball"}/>;
}

export default App;
