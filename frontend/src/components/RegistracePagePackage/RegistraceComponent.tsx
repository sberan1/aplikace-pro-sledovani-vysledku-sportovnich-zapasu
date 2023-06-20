import React, {useContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';
import { UserContext } from '../PrihlaseniPagePackage/UserContext';
import '../PrihlaseniPagePackage/Prihlaseni.css';
import './Registrace.css';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import { Password } from 'primereact/password';
import { Divider } from 'primereact/divider';

const RegistraceComponent = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const { loginUser } = useContext(UserContext);
    const header = <div className="font-bold mb-3">Pick a password</div>;
    const footer = (
        <>
            <Divider />
            <p className="mt-2">Suggestions</p>
            <ul className="pl-2 ml-2 mt-0 line-height-3">
                <li>At least one lowercase</li>
                <li>At least one uppercase</li>
                <li>At least one numeric</li>
                <li>Minimum 8 characters</li>
            </ul>
        </>
    );


    const checkEmail = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/checkEmail', { email: email });
            return response.data.exists;
        } catch (error) {
            console.error('Oj, něco se pokazilo:', error);
            return false;
        }
    };

    const handleRegistration = async () => {
        if (await checkEmail() === false) {
            try {
                const hashedPassword = sha256(password);
                const response = await axios.post('http://localhost:8080/auth/register', {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: hashedPassword
                });
                setLoginStatus(response.data.message);
                await loginUser(email, password);
                setLoginStatus('Registrace proběhla úspěšně. Jste nyní přihlášeni.');
            } catch (error) {
                console.error('Oj, něco se pokazilo:' + error);
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
                           value={firstName} onChange={e => setFirstName(e.target.value)}/>
                </label>
                <label>
                    <p>Příjmení</p>
                    <input type='surname'
                           placeholder="Novák"
                           value={lastName} onChange={e => setLastName(e.target.value)}/>
                </label>
                <label>
                    <p>Email</p>
                    <input type='email'
                           placeholder="pepa@vse.cz"
                           value={email} onChange={e => setEmail(e.target.value)}/>
                </label>
                <label>
                    <p>Heslo</p>
                    <div className="card flex justify-content-center">
                    <Password value={password} onChange={(e) => setPassword(e.target.value)}
                              panelStyle={{backgroundColor: '#1C2227', color: '#ffffffc4'}}
                              header={header}
                              footer={footer}
                              placeholder="**********"
                              type='password'
                    />
                        </div>
                </label>



</div>
            <p className="Chyba">{loginStatus}</p>


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