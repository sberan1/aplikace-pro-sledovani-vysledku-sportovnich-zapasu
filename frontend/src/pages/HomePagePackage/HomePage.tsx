import React from 'react';
import Navbar from './Navbar';
import MainSection from './MainSection';
import Footer from '../../components/footer/Footer';
import './HomePage.css';

interface AppProps {
    HomePage: unknown,
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const HomePage = ({ HomePage, PrihlaseniPage, RegistracePage }: AppProps) => {
    return (
        <div className="HomePaneContainer">
            <Navbar HomePage={HomePage} PrihlaseniPage={PrihlaseniPage} RegistracePage={RegistracePage}/>
            <MainSection/>
            <Footer/>
        </div>
    );
};

export default HomePage;
