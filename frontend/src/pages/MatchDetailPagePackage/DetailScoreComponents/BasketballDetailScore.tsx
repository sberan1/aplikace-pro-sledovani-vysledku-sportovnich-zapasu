import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import '../MatchDetailPage.css';
import sparta from '../../../assets/sparta.png';
import slavia from '../../../assets/slavia.png';
import MatchDetailedScore from "../../../components/MatchDetailedScore/MatchDetailedScore";
import czechFlag from '../../../assets/czechRepublicFlag.svg';
import {BasketballMatchData, FootballMatchData, HockeyMatchData} from "../SportInterfaces";
import axios from "axios";

function BasketballDetailScore ({MatchId}: {MatchId: number}) {

    const [match, setMatch] = useState<BasketballMatchData>(
        {
            id: 1,
            sport: "Basketball",
            date: "2021-05-05",
            time: "20:00",
            homeTeamId: 1,
            awayTeamId: 2,
            homeTeamName: "FC Barcelona",
            awayTeamName: "Real Madrid",
            homeTeamLogo: sparta,
            awayTeamLogo: slavia,
            alreadyPlayed: false,
            favourite: false,
            score : {
                id: 1,
                finalAwayScore: 1,
                finalHomeScore: 2,
                firstQuarterAwayScore: 1,
                firstQuarterHomeScore: 1,
                secondQuarterAwayScore: 0,
                secondQuarterHomeScore: 1,
                thirdQuarterAwayScore: 0,
                thirdQuarterHomeScore: 0,
                fourthQuarterAwayScore: 0,
                fourthQuarterHomeScore: 0,
                overtimeAwayScore: 0,
                overtimeHomeScore: 0,
            },
            leagueName: "Liga mistrů",
            leagueFlag: czechFlag
        }
    );


    const fetchMatches = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/fixture/getFixtureInfoById?id=${MatchId}`);
            setMatch(response.data);
        } catch (error) {
            console.error('Error fetching match detaiů:', error);
        }
    };

    useEffect(() => {
        fetchMatches();
    }, []);

    return (
        <div className={`grid grid-flow-row auto-rows-max`}>
            <MatchDetailedScore text={"1. čtvrtina"} homeScore={match.score.firstQuarterHomeScore} awayScore={match.score.firstQuarterAwayScore}/>
            <MatchDetailedScore text={"2. čtvrtina"} homeScore={match.score.secondQuarterHomeScore} awayScore={match.score.secondQuarterAwayScore}/>
            <MatchDetailedScore text={"3. čtvrtina"} homeScore={match.score.thirdQuarterHomeScore} awayScore={match.score.thirdQuarterAwayScore}/>
            <MatchDetailedScore text={"4. čtvrtina"} homeScore={match.score.fourthQuarterHomeScore} awayScore={match.score.fourthQuarterAwayScore}/>
            <MatchDetailedScore text={"Prodloužení"} homeScore={match.score.overtimeHomeScore} awayScore={match.score.overtimeAwayScore}/>
        </div>
    );
}

export default BasketballDetailScore;