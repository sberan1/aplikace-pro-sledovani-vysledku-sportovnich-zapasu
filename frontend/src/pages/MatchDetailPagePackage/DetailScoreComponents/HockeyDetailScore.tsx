import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import '../MatchDetailPage.css';
import MatchDetailedScore from "../../../components/MatchDetailedScore/MatchDetailedScore";

import {BasketballMatchData, FootballMatchData, HockeyMatchData} from "../SportInterfaces";
import axios from "axios";

function HockeyDetailScore ({MatchId}: {MatchId: number}) {

    const [match, setMatch] = useState<HockeyMatchData>(
        {
            id: 10899,
            sport: "Hockey",
            date: "2023-06-09",
            time: "02:00",
            homeTeamId: 1566,
            awayTeamId: 1585,
            homeTeamName: "Florida Panthers",
            awayTeamName: "Vegas Golden Knights",
            homeTeamLogo: "https://media-3.api-sports.io/hockey/teams/684.png",
            awayTeamLogo: "https://media-3.api-sports.io/hockey/teams/702.png",
            alreadyPlayed: false,
            score: {
                id: 10899,
                finalAwayScore : 2,
                finalHomeScore : 3,
                firstPeriodAwayScore : 1,
                firstPeriodHomeScore : 1,
                secondPeriodAwayScore : 1,
                secondPeriodHomeScore : 0,
                thirdPeriodAwayScore : 0,
                thirdPeriodHomeScore : 1,
                overtimeAwayScore : 0,
                overtimeHomeScore : 0,
                shootoutAwayScore : 0,
                shootoutHomeScore : 0
            },
             leagueName : "NHL",
             leagueFlag : "https://media-2.api-sports.io/hockey/leagues/57.png",
             favourite: false
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
            <MatchDetailedScore text={"1. třetina"} homeScore={match.score.firstPeriodHomeScore} awayScore={match.score.firstPeriodAwayScore}/>
            <MatchDetailedScore text={"2. třetina"} homeScore={match.score.secondPeriodHomeScore} awayScore={match.score.secondPeriodAwayScore}/>
            <MatchDetailedScore text={"3. třetina"} homeScore={match.score.thirdPeriodHomeScore} awayScore={match.score.thirdPeriodAwayScore}/>
            <MatchDetailedScore text={"Prodloužení"} homeScore={match.score.overtimeHomeScore} awayScore={match.score.overtimeAwayScore}/>
            <MatchDetailedScore text={"Penalty"} homeScore={match.score.shootoutHomeScore} awayScore={match.score.shootoutAwayScore}/>
        </div>
    );
}

export default HockeyDetailScore;