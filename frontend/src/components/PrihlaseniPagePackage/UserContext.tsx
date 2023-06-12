import React, {createContext, useState} from 'react';
import axios from 'axios';
import {sha256} from 'js-sha256';

export const UserContext = createContext({
    isLoggedIn: false,
    loginUser: (email, password) => {},
    logoutUser: () => {}
});

export const UserProvider = ({children}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const loginUser = async (email, password) => {
        try {
            const hashedPassword = sha256(password);
            const response = await axios.post('/api/login', {
                email: email,
                password: hashedPassword
            });

            if (response.data.message) {
                setIsLoggedIn(true);
            }
        } catch (error) {
            console.error('Oj, něco se pokazilo:', error);
        }
    };

    const logoutUser = () => {
        setIsLoggedIn(false);
    };

    return (
        <UserContext.Provider value={{isLoggedIn, loginUser, logoutUser}}>
            {children}
        </UserContext.Provider>
    );
};
