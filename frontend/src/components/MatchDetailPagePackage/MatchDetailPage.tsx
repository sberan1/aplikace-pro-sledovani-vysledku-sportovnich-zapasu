import React from 'react';
//import Navbar from './Navbar';
import MainSection from './MainSection';
import Footer from './Footer';
import './MatchDetailPage.css';
import NavBar from "../NavBar/NavBar";
import { useLocation } from 'react-router-dom';



const MatchDetailPage = (props) => {

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const id = searchParams.get('id');
    const sport = searchParams.get('sport');

    return (
        <div className="HomePaneContainer">
            <NavBar PrihlaseniPage={""}/>
            <MainSection MatchId={Number(id)}/>
            <Footer/>
        </div>
    );
};

export default MatchDetailPage;
