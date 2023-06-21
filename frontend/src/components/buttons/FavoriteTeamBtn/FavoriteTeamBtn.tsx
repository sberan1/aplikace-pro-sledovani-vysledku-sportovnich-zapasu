import React, { useState } from 'react';
import { FaStar, FaRegStar } from "react-icons/fa";
import axios from "axios";

function FavoriteTeamButton({ teamId, type }) {
    const [isFavorite, setIsFavorite] = useState(false);
    const handleClick = async () => {
        const newFavoriteStatus = isFavorite;
        setIsFavorite(newFavoriteStatus);
        let response;
        if (isFavorite && type === "team"){
            response = axios.delete(`http://localhost:8080/user/removeFavoriteTeam/${teamId}`);
        }
        else if (!isFavorite && type === "team"){
            response = axios.put(`http://localhost:8080/user/addFavoriteTeam/${teamId}`);
        }
        else if (isFavorite && type === "fixture"){
            response = axios.delete(`http://localhost:8080/user/removeFavoriteFixture/${teamId}`);
        }
        else if (!isFavorite && type === "fixture"){
            response = axios.put(`http://localhost:8080/user/addFavoriteFixture/${teamId}`);
        }


        if (response.status !== 200){
            console.error(`Něco se nepovedlo na updatu oblibeneho tymu: ${response.message}`);
            setIsFavorite(!newFavoriteStatus);
        }
    };

    return (
        <button onClick={handleClick}>
            {isFavorite ? <FaStar color={"#D3FC01"}/> : <FaRegStar color={"#D3FC01"}/>}
        </button>
    );
}

export default FavoriteTeamButton;
