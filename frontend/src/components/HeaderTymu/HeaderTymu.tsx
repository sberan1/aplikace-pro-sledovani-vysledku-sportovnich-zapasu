import React, { useState, useEffect } from 'react';
import './HeaderTymu.css';
import FavoriteTeamBtn from "../FavoriteTeamBtn/FavoriteTeamBtn";

async function getTeamData(teamId) {

    const testovaciData = {
        name: 'Testovací tým',
        focus: 'Fotbal',
        country: 'Česká republika',
        flagUrl: 'https://example.com/flag.png',
        logoUrl: 'https://example.com/logo.png'
    };

    return testovaciData;
}

const TeamComponent = ({ teamId, userId }) => {
    const [team, setTeam] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        getTeamData(teamId)
            .then(data => {
                setTeam(data);
            })
            .catch(error => {
                setError(error);
            });
    }, [teamId]);

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (team === null) {
        return <div>Loading...</div>;
    }

    return (
        <div className="team-component">
            <img src={team.logoUrl} alt={`${team.name} logo`} className="team-logo"/>
            <h2>{team.name}</h2>
            <p>{team.focus}</p>
            <div className="team-origin">
                <img src={team.flagUrl} alt={`${team.country} flag`} className="team-flag"/>
                <span>{team.country}</span>
            </div>
            <FavoriteTeamBtn teamId={teamId} userId={userId}/>
        </div>
    );
}

export default TeamComponent;
