import React, {useContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';
import { UserContext } from './UserContext';
import './Prihlaseni.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import Cookies from 'universal-cookie';

const PrihlaseniComponent = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const { loginUser } = useContext(UserContext);
    const cookies = new Cookies();

    const handleLogin = async () => {
        try {
            const hashedPassword = sha256(password);
            const response = await axios.post('http://localhost:8080/user/authenticate', {
                email: email,
                hashedPassword: hashedPassword
            });
            cookies.set('token', response.data.token, { path: '/', expires: new Date(Date.now() + 1000 * 60 * 60 * 10) });
            setLoginStatus(response.data.message);
            await loginUser(email, password);
            setLoginStatus('Přihlášení bylo úspěšné.');
            console.log(cookies.get('token'));
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
                {loginStatus && <p>{loginStatus}</p>}
            </div>

            <div className="NemamUcet">
                <p className="p1">Nemáte účet?</p>
                <p>To nevadí, můžete se <span><Link to="/registrace">registrovat zde.</Link></span></p>
            </div>
        </div>
    );
};

export default PrihlaseniComponent;
