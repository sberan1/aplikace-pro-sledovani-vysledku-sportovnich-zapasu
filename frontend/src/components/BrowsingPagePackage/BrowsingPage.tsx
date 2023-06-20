import React from 'react';
//import Navbar from './Navbar';
import MainSection from './MainSection';
import Footer from './Footer';
import './BrowsingPage.css';
import NavBar from "../NavBar/NavBar";

export interface AppProps {
    Sport: string
}

const BrowsingPage = ({ Sport }: AppProps) => {
    return (
        <div className="HomePaneContainer">
            <NavBar PrihlaseniPage={""}/>
            <MainSection Sport={Sport} />
            <Footer/>
        </div>
    );
};

export default BrowsingPage;
