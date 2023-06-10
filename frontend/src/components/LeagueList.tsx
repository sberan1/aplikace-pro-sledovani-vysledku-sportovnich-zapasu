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

    return (
        <div>
            <h1 className="text-5xl">Leagues</h1>
            <ul>
                {leagues.map(league => (
                    <li key={league.id}>{league.name} {league.type}</li>
                ))}
            </ul>
        </div>
    );
};

export default LeagueList;
