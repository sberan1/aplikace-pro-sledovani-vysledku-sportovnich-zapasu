import React from 'react';
import NavBar from '../NavBar/NavBar';
import Footer from '../HomePagePackage/Footer';
import './Prihlaseni.css';
import PrihlaseniComponent from "./PrihlaseniComponent";

interface AppProps {
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const PrihlaseniPage = ({PrihlaseniPage, RegistracePage}: AppProps) => {
    return (
            <div className="PrihlaseniPaneContainer">
                <NavBar PrihlaseniPage=""/>
                <div className="PrihlaseniContainer">
                    <h1>Přihlášení</h1>
                    <div className="PrihlaseniComponent">
                        <PrihlaseniComponent />
                    </div>
                </div>
                <Footer/>
            </div>
    );
};

export default PrihlaseniPage;
