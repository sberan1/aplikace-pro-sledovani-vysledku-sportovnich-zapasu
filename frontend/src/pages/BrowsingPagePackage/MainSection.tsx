import React from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './BrowsingPage.css';
import {AppProps} from './BrowsingPage';
import ContentHolder from "../../components/BrowsingContentHolder/ContentHolder";

const MainSection = ({ Sport }: AppProps) => {
    return (
        <section className="MainSection">
            <div className={`MainSectionContainer flex justify-center`}>
                <div className="imgFotbalista"></div>
                <div className={`${ContentHolder}`}>
                    <ContentHolder sport={Sport}/>
                </div>
            </div>
        </section>
    );
};

export default MainSection;
