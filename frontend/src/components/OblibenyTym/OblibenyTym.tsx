import styles from "../Match/Match.module.css";
import {useState} from "react";
import {FaRegStar, FaStar} from "react-icons/fa";
import {BrowserRouter as Router, Route, Link, NavLink, useNavigate} from 'react-router-dom';
import FavouriteStar from "../FavouriteStar/FavouriteStar";
import "../../pages/DashboardPage/Dashboard.css";
import * as url from "url";


function OblibenyTym({teamId, teamName, teamLogo, isFavourite}: {
    teamId: number,
    teamName: string,
    isFavourite: boolean,
    teamLogo: string
}) {

    const navigate = useNavigate();

    const [isFavorite, setIsFavorite] = useState(isFavourite);

    function teamOnClicked() {
        const nextURL = `/teamDetail?teamId=${teamId}`;
        window.location.href = nextURL;
    }

    return (
        <div className="OblibenyTym">
            <div className="TeamContainer">
                <span className="TeamLogo">
                    <img src={teamLogo} alt="Logo"/>
                </span>
                <button onClick={teamOnClicked} className={`pr-3 content-center ${styles.team}`}>
                    {teamName}
                </button>
            </div>
            <FavouriteStar Id={teamId} Type={"Team"} isFav={isFavorite}/>
        </div>

    );
}

export default OblibenyTym;