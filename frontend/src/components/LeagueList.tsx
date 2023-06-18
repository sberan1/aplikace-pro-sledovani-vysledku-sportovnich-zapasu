import React, {useEffect, useState} from 'react';
import League from "./League/League";
import MatchList from "./MatchList";
import {MatchSourceType} from "./Enums";
import Match from "./Match/Match";

const LeagueList = ({sport, date} : { sport: string; date: string }) =>
{
    const [leagues, setLeagues] = useState([]);

    const fetchLeagues = async () => {
        try {
            const response = await fetch(`http://localhost:8080/league/getLeaguesByFixturePlayedAtDateInSport?sport=${sport}&date=${date}`);
            const data = await response.json();
            setLeagues(data);
        } catch (error) {
            console.error('Error fetching leagues:', error);
        }
    };

    useEffect(() => {
        fetchLeagues();
    }, [date]);


    if(leagues.length === 0) {
        return <div className={`font-classic text-white`}>Žádné zápasy</div>
    }

    return (
        <div>
            {
                leagues.map(item => (
                    <League
                        id={Number(item.id)}
                        name={item.name}
                        flagSource={item.flag}
                        sport={sport}
                        date={date}
                    />
                ))
            }
        </div>
    )

};

export default LeagueList;
