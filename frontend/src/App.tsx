import React, {ReactNode} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import Match from './components/match/Match';
import LeagueList from './components/LeagueList';
import Navbar from './components/HomePagePackage/Navbar';
import HomePage from './components/HomePagePackage/HomePage';
import PrihlaseniPage from './components/PrihlaseniPagePackage/PrihlaseniPage';
import {UserProvider} from "./components/PrihlaseniPagePackage/UserContext";
import RegistracePage from './components/RegistracePagePackage/RegistracePage';
import FotbalPage from './components/ZkusebniPageFotbalu/FotbalPage';
import Modal from 'react-modal';

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
                </Routes>
            </Router>
        </UserProvider>
    );
};

export default App;
