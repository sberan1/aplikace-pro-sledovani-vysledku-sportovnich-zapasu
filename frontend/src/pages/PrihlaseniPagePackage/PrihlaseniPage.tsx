import React, {useContext} from 'react';
import NavBar from '../../components/NavBar/NavBar';
import Footer from '../../components/footer/Footer';
import './Prihlaseni.css';
import PrihlaseniComponent from "./PrihlaseniComponent";
import Cookies from "universal-cookie";
import {UserContext} from "./UserContext";

interface AppProps {
    PrihlaseniPage: unknown,
    RegistracePage: unknown
}

const PrihlaseniPage = ({PrihlaseniPage, RegistracePage}: AppProps) => {
    const cookies = new Cookies();
    const {isLoggedIn} = useContext(UserContext);
   if (isLoggedIn === false || cookies.get('token') === undefined) {
       return (
           <div className="PrihlaseniPaneContainer">
               <NavBar PrihlaseniPage=""/>
               <div className="PrihlaseniContainer">
                   <h1>Přihlášení</h1>
                   <div className="PrihlaseniComponent">
                       <PrihlaseniComponent/>
                   </div>
               </div>
               <Footer/>
           </div>
       );
   } else {
       const nextURL = `/dashboard`;
       window.location.href = nextURL;
   }
};

export default PrihlaseniPage;
