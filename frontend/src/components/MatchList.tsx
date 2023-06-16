import React, { useEffect, useState } from 'react';
import {MatchType} from "./Types";
import {MatchSourceType} from "./Enums";
import Match from "./Match/Match";


const MatchList = (type : MatchSourceType, webParams : String) => {
    const [matches, setMatches] = useState([]);

    useEffect(() => {
        const fetchMatches = async () => {
            try {
                if(type = MatchSourceType.Team){
                    const response = await fetch('http://localhost:8080/match/getMatchesFromTeam?' + webParams); //není dokončeno
                    const data = await response.json();
                    setMatches(data);
                }
                if(type = MatchSourceType.League) {
                    const response = await fetch('http://localhost:8080/fixture/getFixturesBySportAndDate' + webParams);
                    const data = await response.json();
                    setMatches(data);
                }
            } catch (error) {
                console.error('Error fetching team of league:', error);
            }
        };

        fetchMatches();
    }, []);

    let matchList: Array<JSX.Element> = [];

    for (let i = 0; i < matches.length; i++) {
        matchList.push(
            <Match
                id={Number(matches[i].id)}
                date={matches[i].date}
                time={matches[i].time}
                team1={matches[i].homeTeam}
                team2={matches[i].awayTeam}
                score1={matches[i].homeTeamScore}
                score2={matches[i].awayTeamScore}
                imgSource1={matches[i].homeTeamLogo}
                imgSource2={matches[i].awayTeamLogo}
            />
        );
    }

    return matchList;
};

export default MatchList;
