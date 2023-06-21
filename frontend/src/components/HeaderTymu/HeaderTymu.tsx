import React, { useState, useEffect } from 'react';
import './HeaderTymu.css';
import FavoriteTeamBtn from "../buttons/FavoriteTeamBtn/FavoriteTeamBtn";

async function getTeamData(teamId) {

    {/*
    const response = await fetch(`https://NASE_API.com/teams/${teamId}`);
    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    const teamData = await response.json();
    return teamData;
    */}

    const testovaciData = {
        name: 'Estadio Israel Barrios',
        focus: 'Fotbalový tým',
        country: 'Guatemala',
        flagUrl: 'https:\/\/media-3.api-sports.io\/football\/teams\/3640.png',
        logoUrl: 'https:\/\/media-2.api-sports.io\/football\/teams\/3653.png'
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
            <div className="left">
                <div className="team-info">
                    <div>
                        <h2>{team.name}</h2>
                        <p className="team-focus">{team.focus}</p>
                    </div>
                    <div className="team-origin">
                        <img src={team.flagUrl} alt={`${team.country} flag`} className="team-flag"/>
                        <span>{team.country}</span>
                    </div>
                </div>
                <div className="middle">
                    <FavoriteTeamBtn teamId={teamId} userId={userId}/>
                </div>
            </div>
            <div className="right">
                <img src={team.logoUrl} alt={`${team.name} logo`} className="team-logo"/>
            </div>
        </div>
    );
}

export default TeamComponent;
