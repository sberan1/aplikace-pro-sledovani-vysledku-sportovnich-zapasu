import React from 'react';
//import Navbar from './Navbar';
import MainSection from './MainSection';
import Footer from "../../components/footer/Footer";
import './MatchDetailPage.css';
import NavBar from "../../components/NavBar/NavBar";
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
