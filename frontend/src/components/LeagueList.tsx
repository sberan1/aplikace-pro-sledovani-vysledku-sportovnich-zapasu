import React, { useEffect, useState } from 'react';

const LeagueList = () => {
    const [leagues, setLeagues] = useState([]);

    useEffect(() => {
        const fetchLeagues = async () => {
            try {
                const response = await fetch('http://localhost:8080/league/getLeagues?sport=Hockey');
                const data = await response.json();
                setLeagues(data);
            } catch (error) {
                console.error('Error fetching leagues:', error);
            }
        };

        fetchLeagues();
    }, []);

    type league = {
        id: bigint,
        name: string,
        type: any,
    }



    let leagueList : Array<league> = [];

    for (let i = 0; i < leagues.length; i++) {
        leagueList.push(leagues[i].id, leagues[i].name,leagues[i].type);
    }



   return leagueList;

};

export default LeagueList;
