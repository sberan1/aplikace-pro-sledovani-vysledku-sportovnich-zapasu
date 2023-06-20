import React, {createContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';

export const UserContext = createContext({
    isLoggedIn: false,
    user: null,
    loginUser: (email, password) => {},
    logoutUser: () => {}
});

export const UserProvider = ({children}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [user, setUser] = useState(null); // Přidán stav pro 'user'

    const loginUser = async (email, password) => {
        try {
            const hashedPassword = sha256(password);
            const response = await axios.post('http://localhost:8080/user/authenticate', {
                email: email,
                password: hashedPassword
            });

            if (response.data.success) {  // kontrola, jestli je 'success' true
                setIsLoggedIn(true);
                setUser(response.data.user); // nastaven 'user' na údaje vrácené z be
            }
        } catch (error) {
            console.error('A sakra tady jdeme znovu, něco se pokazilo:', error);
        }
    };

    const logoutUser = () => {
        setIsLoggedIn(false); // Odhlásit uživatele
        setUser(null); // Odstraneni dat uživatele
        localStorage.removeItem('user'); // Odstraneni dat uživatele z localStorage
    };

    return (
        <UserContext.Provider value={{isLoggedIn, user, loginUser, logoutUser}}>
            {children}
        </UserContext.Provider>
    );
};
