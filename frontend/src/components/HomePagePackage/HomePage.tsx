import React from 'react';
import Navbar from './Navbar';
import MainSection from './MainSection';
import Footer from './Footer';

interface AppProps {
    HomePage: unknown,
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const HomePage = ({ HomePage, PrihlaseniPage, RegistracePage }: AppProps) => {
    return (
        <div>
            <Navbar HomePage={HomePage} PrihlaseniPage={PrihlaseniPage} RegistracePage={RegistracePage}/>
            <MainSection/>
            <Footer/>
        </div>
    );
};

export default HomePage;
