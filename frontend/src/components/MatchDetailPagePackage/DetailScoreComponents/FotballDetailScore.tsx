import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import '../Navbar.css';
import '../MatchDetailPage.css';
import ContentHolder from "../../BrowsingContentHolder/ContentHolder";
import styles from "../../Match/Match.module.css";
import sparta from '../../../assets/sparta.png';
import slavia from '../../../assets/slavia.png';
import MatchDetailedScore from "../../MatchDetailedScore/MatchDetailedScore";
import czechFlag from '../../../assets/czechRepublicFlag.svg';

import {BasketballMatchData, FootballMatchData, HockeyMatchData} from "../SportInterfaces";

function FotballDetailScore ({MatchId}: {MatchId: number}) {

    const [match, setMatch] = useState<FootballMatchData>();

    useEffect(() => {
        fetchMatches();
    }, []);

    const fetchMatches = async () => {
        try {
            const response = await fetch(`http://localhost:8080/`);
            const data = await response.json();
            setMatch(data);
        } catch (error) {
            console.error('Error fetching match detaiů:', error);
        }
    };

    return (
        <div className={`grid grid-flow-row auto-rows-max`}>
            <MatchDetailedScore text={"1. polovina"} homeScore={match.score.firstHalfHomeScore} awayScore={match.score.firstHalfAwayScore}/>
            <MatchDetailedScore text={"2. polovina"} homeScore={match.score.secondHalfHomeScore} awayScore={match.score.secondHalfAwayScore}/>
            <MatchDetailedScore text={"Prodloužení"} homeScore={match.score.overtimeHomeScore} awayScore={match.score.overtimeAwayScore}/>
            <MatchDetailedScore text={"Penalty"} homeScore={match.score.penaltyHomeScore} awayScore={match.score.penaltyAwayScore}/>
        </div>
    );
}

export default FotballDetailScore;