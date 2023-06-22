import React, {useContext} from 'react';
import NavBar from '../../components/NavBar/NavBar';
import Footer from '../../components/footer/Footer';
import '../PrihlaseniPagePackage/Prihlaseni.css';
import './Registrace.css';
import RegistraceComponent from "./RegistraceComponent";
import Cookies from "universal-cookie";
import {UserContext} from "../PrihlaseniPagePackage/UserContext";

interface AppProps {
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const RegistracePage = ({PrihlaseniPage, RegistracePage}: AppProps) => {
    const cookies = new Cookies();
    const {isLoggedIn} = useContext(UserContext);
    if (isLoggedIn === false || cookies.get('token') === undefined) {
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
} else {
    const nextURL = `/dashboard`;
    window.location.href = nextURL;
}
};

export default RegistracePage;
