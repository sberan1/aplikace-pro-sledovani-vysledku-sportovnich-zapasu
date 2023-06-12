import React, {FormEvent, useContext, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link, NavLink} from 'react-router-dom';
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
                    <NavLink to="/hokej" className="NavbarLink">
                        <p>Hokej</p>
                    </NavLink>
                </div>
                <div className="menu-item">
                    <NavLink to="/basketbal" className="NavbarLink">
                        <p>Basketbal</p>
                    </NavLink>
                </div>
                <div className="menu-item">
                    <NavLink to="/fotbal" className="NavbarLink">
                        <p>Fotbal</p>
                    </NavLink>
                </div>
                <div className="menu-item">
                    <NavLink to="/volejbal" className="NavbarLink">
                        <p>Volejbal</p>
                    </NavLink>
                </div>
            </div>

            {/*
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
            )}*/}

            <div className="UserPrihlasen">
                <button className="UserIco">
                </button>
                <div className="DropdownMenu">
                    <ul>
                        <li>Změna hesla</li>
                        <li>Zrušit účet</li>
                        <li>Odhlásit se</li>
                    </ul>
                </div>
            </div>

        </nav>
    );
};

export default NavBar;

