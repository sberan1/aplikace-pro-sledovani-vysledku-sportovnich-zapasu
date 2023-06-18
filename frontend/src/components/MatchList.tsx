import React, { useEffect, useState } from 'react';
import {MatchType} from "./Types";
import {MatchSourceType} from "./Enums";
import Match from "./Match/Match";


const MatchList = ( {type, webParams} : {
    type: MatchSourceType;
    webParams: String;
}) => {
    const [matches, setMatches] = useState([]);


    const fetchMatches = async () => {
        try {
            if(type === MatchSourceType.Team){
                const response = await fetch('http://localhost:8080/match/getMatchesFromTeam?' + webParams); //není dokončeno
                const data = await response.json();
                setMatches(data);
            }
            if(type === MatchSourceType.League) {
                const response = await fetch('http://localhost:8080/fixture/getFixturesBySportAndDate' + webParams);
                const data = await response.json();
                setMatches(data);
            }
        } catch (error) {
            console.error('Error fetching team of league:', error);
        }
    };

    useEffect(() => {
        fetchMatches();
    }, []);

    if(matches.length === 0) {
        return <div>Žádné zápasy</div>
    }


    return (
        <div>
            {
                matches.map(item => (
                        <Match
                            id={Number(item.id)}
                            date={item.date}
                            time={item.time}
                            team1={item.homeTeam}
                            team2={item.awayTeam}
                            score1={item.homeTeamScore}
                            score2={item.awayTeamScore}
                            imgSource1={item.homeTeamLogo}
                            imgSource2={item.awayTeamLogo}
                        />
                ))
            }
        </div>
    )


};

export default MatchList;
