import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import '../MatchDetailPage.css';
import MatchDetailedScore from "../../../components/MatchDetailedScore/MatchDetailedScore";

import {BasketballMatchData, FootballMatchData, HockeyMatchData} from "../SportInterfaces";
import axios from "axios";

function FotballDetailScore ({MatchId}: {MatchId: number}) {

    const [match, setMatch] = useState<FootballMatchData>(
        {
             id : 23245,
             sport :  "Football",
             date :  "2023-06-06",
             time :  "02:10",
             homeTeamId : 3788,
             awayTeamId : 3774,
             homeTeamName : "Racing Cordoba",
             awayTeamName : "Quilmes",
             homeTeamLogo : "https://media-3.api-sports.io/football/teams/1957.png",
             awayTeamLogo : "https://media-1.api-sports.io/football/teams/480.png",
             alreadyPlayed : false,
             score : {
                 id : 23245,
                 finalAwayScore : 1,
                 finalHomeScore : 0,
                 firstHalfAwayScore : 1,
                 firstHalfHomeScore : 0,
                 secondHalfAwayScore : 1,
                 secondHalfHomeScore : 0,
                 overtimeAwayScore : 0,
                 overtimeHomeScore : 0,
                 penaltyAwayScore : 0,
                 penaltyHomeScore : 0
            },
             leagueName : "Primera Nacional",
             leagueFlag : "https://media-2.api-sports.io/football/leagues/129.png",
             favourite : false
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
            <MatchDetailedScore text={"1. poločas"} homeScore={match.score.firstHalfHomeScore} awayScore={match.score.firstHalfAwayScore}/>
            <MatchDetailedScore text={"2. poločas"} homeScore={match.score.secondHalfHomeScore} awayScore={match.score.secondHalfAwayScore}/>
            <MatchDetailedScore text={"Prodloužení"} homeScore={match.score.overtimeHomeScore} awayScore={match.score.overtimeAwayScore}/>
            <MatchDetailedScore text={"Penalty"} homeScore={match.score.penaltyHomeScore} awayScore={match.score.penaltyAwayScore}/>
        </div>
    );
}

export default FotballDetailScore;