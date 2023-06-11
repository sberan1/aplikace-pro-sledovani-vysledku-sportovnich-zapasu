import React from 'react';
// @ts-ignore
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import './Navbar.css';
import '../../App.css';
import AppProps from "../../App";

interface NavbarProps {
    HomePage: unknown,
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const Navbar = ({ HomePage, PrihlaseniPage, RegistracePage }: NavbarProps) => {
    return (
        <nav>
            <div className="logo">
                <span className="navbar-logo"></span>
            </div>

            <input type="text" placeholder="Vyhledejte konkrétní tým nebo zápas"/>

            <div className="buttons">
                <Link to="/registrace">
                    <button className="button registrace">Registrace</button>
                </Link>
                <Link to="/prihlaseni">
                    <button className="button prihlaseni">Přihlásit se</button>
                </Link>
            </div>

        </nav>
    );
};

export default Navbar;

