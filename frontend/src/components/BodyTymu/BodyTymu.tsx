import React, { useState, useEffect } from 'react';
import './BodyTymu.css';
import DatePicker from "../DatePicker/DatePicker";
import DatePickerComponent from "../DatePicker/DatePicker";
import {match} from "assert";
import {MatchSourceType} from "../Enums";
import MatchList from "../MatchList";
import axios from "axios";

const BodyTymu = ({teamId}) => {
    const [team, setTeam] = useState(null);
    const [error, setError] = useState(null);
    const { formattedDateToReturn, render } = DatePicker();

    async function getTeamData(teamId) {
            const response = await axios.get(`http://localhost:8080/fixture/getFixturesByTeamIdAndDateFromToday/${teamId}`);
            if (response.status !== 200) {
                setError(response.data.message)
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            setTeam(response.data);
        }

        useEffect(() => {
            getTeamData(teamId);
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
                    {render}
                </div>
                {/*   <MatchList type = {MatchSourceType.Team} webParams = {`?id=${match}`} />   původne: {`?id=${match.id}`} */}
            </div>
        );
    }
  export default BodyTymu;





