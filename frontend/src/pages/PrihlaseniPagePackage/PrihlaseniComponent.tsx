import React, {useContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';
import { UserContext } from './UserContext';
import './Prihlaseni.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';

const PrihlaseniComponent = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const { loginUser } = useContext(UserContext);
    const {isLoggedIn} = useContext(UserContext);

    const handleLogin = async () => {
        try {
            const hashedPassword = sha256(password);
            const response = await axios.post('http://localhost:8080/auth/authenticate', {
                email: email,
                hashedPassword: hashedPassword
            });
            setLoginStatus(response.data.message);
            await loginUser(email, password);
            setLoginStatus('Přihlášení bylo úspěšné.');
            const nextURL = `/dashboard`;
            window.location.href = nextURL;
        } catch (error) {
            console.error('Oj, něco se pokazilo:', error);
            setLoginStatus('Něco se pokazilo při přihlašování. Zkuste to prosím znovu.');
        }
    };

    return (
        <div className="PrihlaseniForm">
            <h2>Zadejte prosím Vaše přihlašovací údaje </h2>
            <div className="InputContainer">
                <label>
                    <p>Email</p>
                    <input type='email'
                           placeholder="pepa@vse.cz"
                           value={email} onChange={e => setEmail(e.target.value)}/>
                </label>
                <label>
                    <p>Heslo</p>
                    <input type='password'
                           placeholder="**********"
                           value={password} onChange={e => setPassword(e.target.value)}/>
                </label>
            </div>

            <div className="buttons">
                <Link to="/">
                    <button>Zrušit</button>
                </Link>

                <button className="button" onClick={handleLogin}>Přihlásit se</button>
            </div>
            {loginStatus && <p className={isLoggedIn === true ? "Uspech"  : "Chyba"}>{loginStatus}</p>}
            <div className="NemamUcet">
                <p className="p1">Nemáte účet?</p>
                <p>To nevadí, můžete se <span><Link to="/registrace">registrovat zde.</Link></span></p>
            </div>
        </div>
    );
};

export default PrihlaseniComponent;
