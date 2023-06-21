import React, {createContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';
import Cookies from 'universal-cookie';


const cookies = new Cookies();
var config = {};

export const UserContext = createContext({
    isLoggedIn: false,
    user: null,
    loginUser: (email, password) => {},
    logoutUser: () => {},
    refreshToken: () => {}
});

export const UserProvider = ({children}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [user, setUser] = useState(null); // Přidán stav pro 'user'

    const loginUser = async (email, password) => {
        try {
            const hashedPassword = sha256(password);
            const response = await axios.post('http://localhost:8080/auth/authenticate', {
                email: email,
                hashedPassword: hashedPassword
            });

            if (response.status === 200) {  // kontrola, jestli je 'success' true
                cookies.set('token', response.data.token, { path: '/', expires: new Date(Date.now() + 1000 * 60 * 60 * 10) });
                setIsLoggedIn(true);
                setUser(axios.get('http://localhost:8080/auth/getUserInfo')); // nastaven 'user' na údaje vrácené z be
                console.log(cookies.get('token'));
            }
        } catch (error) {
            console.error('A sakra tady jdeme znovu, něco se pokazilo:', error);
        }
    };

    const logoutUser = () => {

        setIsLoggedIn(false); // Odhlásit uživatele
        setUser(null); // Odstraneni dat uživatele
        localStorage.removeItem('user'); // Odstraneni dat uživatele z localStorage
        cookies.remove('token'); // Odstraneni tokenu z cookies
    };

    const refreshToken =  () => {
        axios.post('http://localhost:8080/user/refreshToken')
            .then((response) => {
            cookies.set('token', response.data.token, { path: '/', expires: new Date(Date.now() + 1000 * 60 * 60 * 10) });})
            .catch((error) => {
            console.error('A sakra tady jdeme znovu, něco se pokazilo:', error);
        });
    }

    return (
        <UserContext.Provider value={{isLoggedIn, user, loginUser, logoutUser, refreshToken}}>
            {children}
        </UserContext.Provider>
    );
};
