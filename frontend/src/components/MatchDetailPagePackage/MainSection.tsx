import React, {useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './Navbar.css';
import './MatchDetailPage.css';
import ContentHolder from "../BrowsingContentHolder/ContentHolder";

const MainSection = ({MatchID : number}) => {

    const [match, setMatch] = useState([]);

    const fetchMatches = async () => {
        try {
            const response = await fetch(`http://localhost:8080/`);
            const data = await response.json();
            setMatch(data);
        } catch (error) {
            console.error('Error fetching match detaiů:', error);
        }
    };

    useEffect(() => {

    }, []);


    return (
        <section className="MainSection">
            <div className={`MainSectionContainer flex justify-center`}>
                <div className="imgFotbalista"></div>
                /********************************/


                <div className={``}>
                            ////
                </div>


                /********************************/
            </div>
        </section>
    );
};

export default MainSection;
