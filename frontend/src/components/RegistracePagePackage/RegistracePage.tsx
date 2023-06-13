import React from 'react';
import NavBar from '../NavBar/NavBar';
import Footer from '../HomePagePackage/Footer';
import '../PrihlaseniPagePackage/Prihlaseni.css';
import './Registrace.css';
import RegistraceComponent from "./RegistraceComponent";
import {UserProvider} from "../PrihlaseniPagePackage/UserContext";

interface AppProps {
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const RegistracePage = ({PrihlaseniPage, RegistracePage}: AppProps) => {
    return (
        <div className="PrihlaseniPaneContainer">
            <NavBar PrihlaseniPage=""/>
            <div className="PrihlaseniContainer">
                <h1>Registrace</h1>
                <div className="RegistraceComponent">
                    <RegistraceComponent/>
                </div>
            </div>
            <Footer/>
        </div>
    );
};

export default RegistracePage;
