import React, {FormEvent, useContext, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './NavBar.css';
import '../../App.css';
import AppProps from "../../App";
import {UserContext} from "../PrihlaseniPagePackage/UserContext";


interface NavbarProps {
    PrihlaseniPage: unknown
}

const NavBar = ({PrihlaseniPage}: NavbarProps) => {
    const { isLoggedIn } = useContext(UserContext);

    return (
        <nav>
            <div className="logo">
                <span className="navbar-logo"></span>
            </div>

            <div className="menu-items">
                <div className="menu-item">
                    <Link to="/hokej">
                        <p>Hokej</p>
                    </Link>
                </div>
                <div className="menu-item">
                    <Link to="/basketbal">
                        <p>Basketbal</p>
                    </Link>
                </div>
                <div className="menu-item">
                    <Link to="/fotbal">
                        <p>Fotbal</p>
                    </Link>
                </div>
                <div className="menu-item">
                    <Link to="/volejbal">
                        <p>Volejbal</p>
                    </Link>
                </div>
            </div>

            {isLoggedIn ? (
                <div className="UserPrihlasen">
                    <div className="btn">
                        <ul>
                            <li>Změna hesla</li>
                            <li>Zrušit účet</li>
                            <li>Odhlásit se</li>
                        </ul>
                    </div>
                </div>
            ) : (
                <div className="buttons UserNeprihlasen">
                    <Link to="/prihlaseni">
                        <button className="button prihlaseni">Přihlásit se</button>
                    </Link>
                </div>
            )}

        </nav>
    );
};

export default NavBar;

