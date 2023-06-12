import React, {FormEvent, useContext, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link, NavLink} from 'react-router-dom';
import Modal from 'react-modal';
import './NavBar.css';
import '../../App.css';
import AppProps from "../../App";
import {UserContext} from "../PrihlaseniPagePackage/UserContext";

interface NavbarProps {
    PrihlaseniPage: unknown
}

const NavBar = ({PrihlaseniPage}: NavbarProps) => {
    const { isLoggedIn } = useContext(UserContext);
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [passwordModalIsOpen, setPasswordModalIsOpen] = useState(false);
    const [deleteModalIsOpen, setDeleteModalIsOpen] = useState(false);

    const toggleDropdown = () => {
        setDropdownOpen(!dropdownOpen);
    };

    const openPasswordModal = () => {
        setPasswordModalIsOpen(true);
    };

    const closePasswordModal = () => {
        setPasswordModalIsOpen(false);
    };

    const openDeleteModal = () => {
        setDeleteModalIsOpen(true);
    };

    const closeDeleteModal = () => {
        setDeleteModalIsOpen(false);
    };

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
            ) : (
                <div className="buttons UserNeprihlasen">
                    <Link to="/prihlaseni">
                        <button className="button prihlaseni">Přihlásit se</button>
                    </Link>
                </div>
            )}*/}

            <div className="UserPrihlasen">
                <button className="UserIco" onClick={toggleDropdown}>
                </button>
                {dropdownOpen && (
                    <div className="DropdownMenu">
                        <ul>
                            <li onClick={openPasswordModal}>Změna hesla</li>
                            <li onClick={openDeleteModal}>Zrušit účet</li>
                            <li>Odhlásit se</li>
                        </ul>
                        <Modal
                            isOpen={passwordModalIsOpen}
                            onRequestClose={closePasswordModal}
                            contentLabel="Změna hesla"
                            className="Modal"
                            overlayClassName="Overlay"
                        >
                            <h2>Změna hesla</h2>
                            <p>Toto je obsah modálního okna pro změnu hesla.</p>
                            <button onClick={closePasswordModal}>Zavřít</button>
                        </Modal>
                        <Modal
                            isOpen={deleteModalIsOpen}
                            onRequestClose={closeDeleteModal}
                            contentLabel="Zrušit účet"
                            className="Modal"
                            overlayClassName="Overlay"
                        >
                            <h2>Zrušit účet</h2>
                            <p>Toto je obsah modálního okna pro zrušení účtu.</p>
                            <button onClick={closeDeleteModal}>Zavřít</button>
                        </Modal>
                    </div>
                )}
            </div>
        </nav>
    );
};

export default NavBar;