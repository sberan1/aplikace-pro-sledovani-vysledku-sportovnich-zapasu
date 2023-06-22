import React, {FormEvent, useContext, useState} from 'react';
import {BrowserRouter as Router, Route, Link, NavLink, useNavigate} from 'react-router-dom';
import Modal from 'react-modal';
import './NavBar.css';
import '../../App.css';
import './Modal.css'
import {UserContext} from "../../pages/PrihlaseniPagePackage/UserContext";
import axios from 'axios';
import {sha256} from "js-sha256";
import Cookies from "universal-cookie";

interface NavbarProps {
    PrihlaseniPage: unknown
}

const NavBar = ({PrihlaseniPage}: NavbarProps) => {
    const {isLoggedIn, user, logoutUser} = useContext(UserContext);
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [passwordModalIsOpen, setPasswordModalIsOpen] = useState(false);
    const [deleteModalIsOpen, setDeleteModalIsOpen] = useState(false);
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [changePasswordStatus, setChangePasswordStatus] = useState('');
    const navigate = useNavigate();
    var cookies = new Cookies();
    const logout = () => {
        logoutUser();
        navigate("/");
    };
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

    const changePassword = async () => {

        if (oldPassword === newPassword) {
            alert("Vymysli nové heslo, ty lopato.");
            return;
        }

        try {
            const response = await axios.put(`http://localhost:8080/user/changePassword`,
                {
                oldPassword: sha256(oldPassword),
                newPassword: sha256(newPassword)
            });

            if (response.status === 200) {
                console.log("Heslo bylo úspěšně změněno.");
                setChangePasswordStatus('Heslo bylo úspěšně změněno.');
                closePasswordModal();
            } else if (response.data.error) {
                console.log(response.data.error);
                alert(response.data.error);
                setChangePasswordStatus('Nastala chyba při změně hesla.');
            }
        } catch (error) {
            console.error('Nastala chyba při změně hesla:', error);
        }
    };


    const deleteAccount = async () => {
        try {
            const response = await axios.delete(`http://localhost:8080/user/delete`);
            console.log(response.data);
            setDeleteModalIsOpen(false);  // Zavřít modální okno po úspěšném smazání
            logoutUser(); // odhlaseni
            navigate("/"); // presmerovani na homepage
        } catch (error) {
            console.error('Nastala chyba při mazání účtu:', error);
        }
    };

    return (
        <nav>
            <div className="logo">
                {isLoggedIn || cookies.get('token') !== undefined ? (
                <Link to="/dashboard" className="NavbarLink">
                    <span className="navbar-logo"></span>
                </Link>) : (
                <Link to="/" className="NavbarLink">
                            <span className="navbar-logo"></span>
                </Link>)}
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
            {isLoggedIn || cookies.get('token') !== undefined ? (
                <div className="UserPrihlasen">
                    <button className="UserIco" onClick={toggleDropdown}>
                    </button>
                    {dropdownOpen && (
                        <div className="DropdownMenu">
                            <ul>
                                <li onClick={openPasswordModal}>Změna hesla</li>
                                <li onClick={openDeleteModal}>Zrušit účet</li>
                                <li onClick={logout}>Odhlásit se</li>
                            </ul>

                            <Modal
                                isOpen={passwordModalIsOpen}
                                onRequestClose={closePasswordModal}
                                contentLabel="Změna hesla"
                                className="Modal"
                                overlayClassName="Overlay"
                            >
                                <div className="ModalContainer ZmenaHeslaModal">
                                    <h2>Pro změnu hesla vyplňte pole níže</h2>

                                    <div className="InputContainer">
                                        <label>
                                            <p>Staré heslo</p>
                                            <input type='password'
                                                   placeholder="**********"
                                                   value={oldPassword}
                                                   onChange={e => setOldPassword(e.target.value)}/>
                                        </label>
                                        <label>
                                            <p>Nové heslo</p>
                                            <input type='password'
                                                   placeholder="**********"
                                                   value={newPassword}
                                                   onChange={e => setNewPassword(e.target.value)}/>
                                        </label>
                                    </div>

                                    <div className="btnsContainer">
                                        <button onClick={closePasswordModal} className="btnZrusit">ZRUŠIT</button>
                                        <button onClick={changePassword} className="btnSubmit">Změnit heslo</button>
                                    </div>
                                </div>
                            </Modal>

                            <Modal
                                isOpen={deleteModalIsOpen}
                                onRequestClose={closeDeleteModal}
                                contentLabel="Zrušit účet"
                                className="Modal"
                                overlayClassName="Overlay"
                            >
                                <div className="ModalContainer ZruseniUctuModal">
                                    <h2>Opravdu chcete zrušit svůj účet?</h2>
                                    <div className="btnsContainer">
                                        <button onClick={deleteAccount} className="btnZrusit">ZRUŠIT ÚČET</button>
                                        <button onClick={closeDeleteModal} className="btnSubmit">STORNO</button>
                                    </div>
                                </div>
                            </Modal>
                        </div>
                    )}
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