import React, { useState, useEffect } from 'react';
import './BodyTymu.css';
import DatePicker from "../DatePicker/DatePicker";

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

const BodyTymu = ({teamId}) => {
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
        <div className="BodyTymuContainer">

            <div className="header">
                {/* tady bude komponenta program/vysledky */}
                <DatePicker/>
            </div>

            {/* komponenta zápasů, ale musí se tam nejak pridat filter/omezeni jen pro tenhle
            dany tym */}
        </div>
    );
}

export default BodyTymu;