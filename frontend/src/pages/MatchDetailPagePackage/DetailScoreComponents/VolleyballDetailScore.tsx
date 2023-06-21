import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import '../MatchDetailPage.css';
import MatchDetailedScore from "../../../components/MatchDetailedScore/MatchDetailedScore";
import {VolleyballMatchData} from "../SportInterfaces";
import axios from "axios";


function HockeyDetailScore ({MatchId}: {MatchId: number}) {

    const [match, setMatch] = useState<VolleyballMatchData>(
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
                id: 21883,
                finalAwayScore: 3,
                finalHomeScore: 2,
                firstSetAwayScore: 16,
                firstSetHomeScore: 25,
                secondSetAwayScore: 21,
                secondSetHomeScore: 25,
                thirdSetAwayScore: 25,
                thirdSetHomeScore: 23,
                fourthSetAwayScore: 25,
                fourthSetHomeScore: 21,
                fifthSetAwayScore: 15,
                fifthSetHomeScore: 11
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
            <MatchDetailedScore text={"1. set"} homeScore={match.score.firstSetHomeScore} awayScore={match.score.firstSetAwayScore}/>
            <MatchDetailedScore text={"2. set"} homeScore={match.score.secondSetHomeScore} awayScore={match.score.secondSetAwayScore}/>
            <MatchDetailedScore text={"3. set"} homeScore={match.score.thirdSetHomeScore} awayScore={match.score.thirdSetAwayScore}/>
            <MatchDetailedScore text={"4. set"} homeScore={match.score.fourthSetHomeScore} awayScore={match.score.fourthSetAwayScore}/>
            <MatchDetailedScore text={"5. set"} homeScore={match.score.fifthSetHomeScore} awayScore={match.score.fifthSetAwayScore}/>
        </div>
    );
}

export default HockeyDetailScore;