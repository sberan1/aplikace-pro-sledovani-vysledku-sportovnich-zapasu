import React, {useEffect, useState} from 'react';
import League from "./League/League";
import MatchList from "./MatchList";
import {MatchSourceType} from "./Enums";
import Match from "./Match/Match";
import axios from "axios";

const LeagueList = ({sport, date} : { sport: string; date: string }) =>
{
    const [leagues, setLeagues] = useState([]);

    const fetchLeagues = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/league/getLeaguesByFixturePlayedAtDateInSport?sport=${translatedSport(sport)}&date=${date}`);
            setLeagues(response.data);
        } catch (error) {
            console.error('Error fetching leagues:', error);
        }
    };

    useEffect(() => {
        setLeagues([]);
        fetchLeagues();
    }, [date, sport]);

    const translatedSport = (sport: string) => {
        switch (sport) {
            case 'Fotbal':
                return 'Football';
            case 'Hokej':
                return 'Hockey';
            case 'Basketbal':
                return 'Basketball';
            case 'Volejbal':
                return 'Volleyball';
            default:
                return '';
        }
    }


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
                        sport={translatedSport(sport)}
                        date={date}
                    />
                ))
            }
        </div>
    )

};

export default LeagueList;
