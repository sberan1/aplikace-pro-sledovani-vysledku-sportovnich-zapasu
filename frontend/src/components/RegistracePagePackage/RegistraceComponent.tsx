import React, {useContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';
import { UserContext } from '../PrihlaseniPagePackage/UserContext';
import '../PrihlaseniPagePackage/Prihlaseni.css';
import './Registrace.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';

const RegistraceComponent = () => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const { loginUser } = useContext(UserContext);

    const checkEmail = async () => {
        try {
            const response = await axios.post('/api/checkemail', { email: email });
            return response.data.exists;
        } catch (error) {
            console.error('Oj, něco se pokazilo:', error);
            return false;
        }
    };

    const handleRegistration = async () => {
        if (!await checkEmail()) {
            try {
                const hashedPassword = sha256(password);
                const response = await axios.post('/api/register', {
                    name: name,
                    surname: surname,
                    email: email,
                    password: hashedPassword
                });
                setLoginStatus(response.data.message);
                await loginUser(email, password);
                setLoginStatus('Registrace proběhla úspěšně. Jste nyní přihlášeni.');
            } catch (error) {
                console.error('Oj, něco se pokazilo:', error);
                setLoginStatus('Něco se pokazilo při registraci. Zkuste to prosím znovu.');
                alert('Něco se pokazilo při registraci. Zkuste to prosím znovu.');
            }
        } else {
            setLoginStatus('Tento e-mail je již v systému zaregistrován.');
        }
    };

    return (
        <div className="PrihlaseniForm RegistraceForm">
            <h2>Pro úspěšnou registraci prosím vyplňte následující formulář</h2>
            <div className="InputContainer">
                <label>
                    <p>Jméno</p>
                    <input type='name'
                           placeholder="Pepa"
                           value={name} onChange={e => setName(e.target.value)}/>
                </label>
                <label>
                    <p>Příjmení</p>
                    <input type='surname'
                           placeholder="Novák"
                           value={surname} onChange={e => setSurname(e.target.value)}/>
                </label>
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

                <button onClick={handleRegistration} className="button">Registrovat se</button>

            </div>

            <div className="NemamUcet">
                <p className="p1">Již máte účet?</p>
                <p>Přihlaste se prosím <span><Link to="/prihlaseni">zde.</Link></span></p>
            </div>
        </div>
    );
};

export default RegistraceComponent;