import React, {useState, useEffect, useContext} from 'react';
import './HeaderTymu.css';
import FavoriteTeamBtn from '../Buttons/FavoriteTeamBtn/FavoriteTeamBtn';
import axios from "axios";
import FavouriteStar from "../FavouriteStar/FavouriteStar";
import Cookies from "universal-cookie";
import {UserContext} from "../../pages/PrihlaseniPagePackage/UserContext";

const TeamComponent = ({ teamId }) => {
    const [team, setTeam] = useState({
        id: 3788,
        name: "Racing Cordoba",
        sport: "Football",
        teamLogo: "https://media-3.api-sports.io/football/teams/1957.png",
        country: "Argentina",
        countryLogo: "https://media-1.api-sports.io/flags/ar.svg",
        favourite: true
    });
    var cookies = new Cookies();
    const {isLoggedIn} = useContext(UserContext);

    async function getTeamData(teamId) {

        const response = await axios.get(`http://localhost:8080/team/getTeamInfoById?id=${teamId}`);
        if (response.status!==200) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        setTeam(response.data);
    }

    useEffect(() => {
        getTeamData(teamId);
    }, []);


    return (
        <div className="team-component">
            <div className="left">
                <div className="team-info">
                    <div>
                        <h2>{team.name}</h2>
                        <p className="team-focus">{team.sport}</p>
                    </div>
                    <div className="team-origin">
                        <img src={team.countryLogo} alt={`${team.country} flag`} className="team-flag"/>
                        <span>{team.country}</span>
                    </div>
                </div>
                <div className="middle">{isLoggedIn || cookies.get('token') !== undefined ?(
                    <FavouriteStar Id={teamId} Type="Team" isFav={team.favourite}/>
                ) : (
                    <div />
                )}

                </div>
            </div>
            <div className="right">
                <img src={team.teamLogo} alt={`${team.name} logo`} className="team-logo"/>
            </div>
        </div>
    );
}

export default TeamComponent;
