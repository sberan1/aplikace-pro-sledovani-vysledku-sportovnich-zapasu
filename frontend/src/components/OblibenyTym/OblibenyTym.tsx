import styles from "../Match/Match.module.css";
import {useState} from "react";
import {FaRegStar, FaStar} from "react-icons/fa";
import {BrowserRouter as Router, Route, Link, NavLink, useNavigate} from 'react-router-dom';
import FavouriteStar from "../FavouriteStar/FavouriteStar";
import "../../pages/DashboardPage/Dashboard.css";
import * as url from "url";


function OblibenyTym({teamId, teamName, userId, isFavorite: initialIsFavorite, teamLogo}: {
    teamId: number,
    teamName: string,
    userId: number,
    isFavorite: boolean,
    teamLogo: string
}) {

    const navigate = useNavigate();

    const [isFavorite, setIsFavorite] = useState(true);
    const handleClick = async () => {
        const newFavoriteStatus = !isFavorite;
        setIsFavorite(newFavoriteStatus);

        const url = `http://localhost:3000/favourites/${userId}/${teamId}`;
        const method = newFavoriteStatus ? 'POST' : 'DELETE';

        const response = await fetch(url, {method});

        if (!response.ok) {
            console.error(`Něco se nepovedlo na updatu oblibeneho tymu: ${response.status}`);
            setIsFavorite(!newFavoriteStatus);
        }
    };

    function teamOnClicked() {
        navigate('/tym/${teamId}');
    }

    return (
        <div className="OblibenyTym">
            <div className="TeamContainer">
                <span className="TeamLogo">{teamLogo}</span>
                <button onClick={teamOnClicked} className={`pr-3 content-center ${styles.team}`}>
                    {teamName}
                </button>
            </div>
            <FavouriteStar Id={1} Type={"Team"} isFav/>
        </div>

    );
}

export default OblibenyTym;