import React, {useEffect, useState} from 'react';
import League from "./League/League";
import MatchList from "./MatchList";
import {MatchSourceType} from "./Enums";

const LeagueList = ({sport, date} :
    {
        sport : string,
        date : any
    }) => {
    const [leagues, setLeagues] = useState([]);

    useEffect(() => {
        const fetchLeagues = async () => {
            try {
                const response = await fetch(`http://localhost:8080/league/getLeaguesByFixturePlayedAtDateInSport?sport=${sport}&date=${date}`);
                const data = await response.json();
                setLeagues(data);
            } catch (error) {
                console.error('Error fetching leagues:', error);
            }
        };

        fetchLeagues();
    }, []);



    let leagueList : Array<JSX.Element> = [];

    for (let i = 0; i < leagues.length; i++) {
        leagueList.push(
            <League
                id={Number(leagues[i].id)}
                name={leagues[i].name}
                flagSource={leagues[i].flagSource}
                matchList={MatchList(MatchSourceType.League, `getFixturesBySportAndDate?sport=${sport}&date=${date}&league=${Number(leagues[i].id)}`)}
            />
        );
    }



   return leagueList;

};

export default LeagueList;
