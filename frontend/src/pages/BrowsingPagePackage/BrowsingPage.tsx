import React, {useEffect} from 'react';
import MainSection from './MainSection';
import Footer from "../../components/footer/Footer";
import './BrowsingPage.css';
import NavBar from "../../components/NavBar/NavBar";
import SearchBarComponent from "../../components/SearchBar/SearchBarComponent";

export interface AppProps {
    Sport: string
}

const BrowsingPage = ({ Sport }: AppProps) => {
    return (
        <div className="HomePaneContainer">
            <NavBar PrihlaseniPage={""}/>
            <SearchBarComponent/>
            {/*<MainSection Sport={Sport} />*/}
            <Footer/>
        </div>
    );
};

export default BrowsingPage;
