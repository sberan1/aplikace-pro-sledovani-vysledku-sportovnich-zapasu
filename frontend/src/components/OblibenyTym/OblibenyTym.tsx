import styles from "../Match/Match.module.css";
import {useState} from "react";
import {FaRegStar, FaStar} from "react-icons/fa";
import {BrowserRouter as Router, Route, Link, NavLink, useNavigate} from 'react-router-dom';


function OblibenyTym({teamId, teamName, userId, isFavorite: initialIsFavorite } :  {
    teamId: number,
    teamName: string,
    userId: number,
    isFavorite: boolean
}) {

    const navigate = useNavigate();

    const [isFavorite, setIsFavorite] = useState(true);
    const handleClick = async () => {
        const newFavoriteStatus = !isFavorite;
        setIsFavorite(newFavoriteStatus);

        const url = `http://localhost:3000/favourites/${userId}/${teamId}`;
        const method = newFavoriteStatus ? 'POST' : 'DELETE';

        const response = await fetch(url, { method });

        if (!response.ok) {
            console.error(`Něco se nepovedlo na updatu oblibeneho tymu: ${response.status}`);
            setIsFavorite(!newFavoriteStatus);
        }
    };

    function teamOnClicked() {
        navigate('/tym/${teamId}');
    }

    return (
        /*   <div className="OblibenyTym">
            <button onClick={teamOnClicked} className={`pr-3 content-center ${styles.team}`}>
                {teamName}
            </button>
            <button onClick={handleClick}>
                {isFavorite ? <FaStar color={"#D3FC01"}/> : <FaRegStar color={"#D3FC01"}/>}
            </button>
        </div> */

    <div className="OblibenyTym">
        <button onClick={teamOnClicked} className={`pr-3 content-center ${styles.team}`}>
            Sparta
        </button>
        <button onClick={handleClick}>
            {isFavorite ? <FaStar color={"#D3FC01"}/> : <FaRegStar color={"#D3FC01"}/>}
        </button>
    </div>

    );
}

export default OblibenyTym;