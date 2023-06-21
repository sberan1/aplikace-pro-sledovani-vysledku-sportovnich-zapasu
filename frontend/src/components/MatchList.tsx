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
                const response = await fetch('http://localhost:8080/team/getTeamInfoById' + webParams); //není dokončeno
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
        return <div className={`font-classic text-white pl-4`}>Žádné zápasy</div>
    }


    return (
        <div>
            {
                matches.map(item => (
                        <Match
                            id={Number(item.id)}
                            date={item.date}
                            time={item.time}
                            homeTeamId={item.homeTeamId}
                            awayTeamId={item.awayTeamId}
                            homeTeamName={item.homeTeam}
                            awayTeamName={item.awayTeam}
                            homeTeamScore={item.homeTeamScore}
                            awayTeamScore={item.awayTeamScore}
                            homeTeamLogo={item.homeTeamLogo}
                            awayTeamLogo={item.awayTeamLogo}
                        />
                ))
            }
        </div>
    )


};

export default MatchList;
