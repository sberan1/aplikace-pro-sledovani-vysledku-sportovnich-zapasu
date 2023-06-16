import React, {ReactNode} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import Match from './components/match/Match';
import LeagueList from './components/LeagueList';
import Navbar from './pages/HomePagePackage/Navbar';
import HomePage from './pages/HomePagePackage/HomePage';
import PrihlaseniPage from './pages/PrihlaseniPagePackage/PrihlaseniPage';
import {UserProvider} from "./pages/PrihlaseniPagePackage/UserContext";
import RegistracePage from './pages/RegistracePagePackage/RegistracePage';
import FotbalPage from './components/ZkusebniPageFotbalu/FotbalPage';
import Modal from 'react-modal';
import ProfilTymu from "./pages/ProfilTymuPage/ProfilTymu";

Modal.setAppElement('#root');

const App = () => {
    return (
        <UserProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<HomePage HomePage="" PrihlaseniPage="" RegistracePage=""/>}/>
                    <Route path="/prihlaseni" element={<PrihlaseniPage PrihlaseniPage="" RegistracePage=""/>}/>
                    <Route path="/registrace" element={<RegistracePage  PrihlaseniPage="" RegistracePage=""/>} />
                    <Route path="/fotbal" element={<FotbalPage />}></Route>
                    <Route path={"/team/:id"} element={<ProfilTymu teamId={undefined}/>}/>
                </Routes>
            </Router>
        </UserProvider>
    );
};

export default App;
