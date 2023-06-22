import React, { useEffect, useState } from 'react';
import {MatchType} from "./Types";
import {MatchSourceType} from "./Enums";
import Match from "./Match/Match";
import axios from "axios";

const MatchList = ( {type, webParams} : {
    type: MatchSourceType;
    webParams: String;
}) => {
    const [matches, setMatches] = useState([]);


    const fetchMatches = async () => {
        try {
            if(type === MatchSourceType.TeamPast){
                const response = await axios.get('http://localhost:8080/fixture/getFixturesByTeamIdAndDateBeforeToday/' + webParams); //není dokončeno
                setMatches(response.data);
            }
            if(type === MatchSourceType.TeamFuture){
                const response = await axios.get('http://localhost:8080/fixture/getFixturesByTeamIdAndDateFromToday/' + webParams); //není dokončeno
                setMatches(response.data);
            }
            if(type === MatchSourceType.League) {
                const response = await axios.get('http://localhost:8080/fixture/getFixturesBySportAndDate' + webParams);
                setMatches(response.data);
            }
            if (type === MatchSourceType.User) {
                const response = await axios.get('http://localhost:8080/user/getFavouriteFixtures');
                setMatches(response.data);
            }
        } catch (error) {
            console.error('Error fetching team of league:', error);
        }
    };

    useEffect(() => {
        fetchMatches();
    }, [type, webParams]);

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
