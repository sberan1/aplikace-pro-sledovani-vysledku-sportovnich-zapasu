import React, { useEffect, useState } from 'react';

const LeagueList = () => {
    const [leagues, setLeagues] = useState([]);

    useEffect(() => {
        const fetchLeagues = async () => {
            try {
                const response = await fetch('http://localhost:8080/league/getLeaguesByFixturePlayedAtDateInSport?sport=Basketball&date=2023-05-15');
                const data = await response.json();
                setLeagues(data);
            } catch (error) {
                console.error('Error fetching leagues:', error);
            }
        };

        fetchLeagues();
    }, []);

    type league = {
        id: number,
        name: string,
        flagSource: string
    }



    let leagueList : Array<league> = [];

    for (let i = 0; i < leagues.length; i++) {
        leagueList.push(leagues[i].id, leagues[i].name,leagues[i].flagSource);
    }



   return leagueList;

};

export default LeagueList;
