import React from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './BrowsingPage.css';
import {AppProps} from './BrowsingPage';
import ContentHolder from "../../components/BrowsingContentHolder/ContentHolder";
import SearchBarComponent from "../../components/SearchBar/SearchBarComponent";

const MainSection = ({ Sport }: AppProps) => {
    return (
        <section className="MainSection">
            <div className={'MainSectionContainer flex justify-center'}>
                <div className="imgFotbalista"></div>

                <ContentHolder sport={Sport}/>


            </div>
        </section>
    );
};

export default MainSection;