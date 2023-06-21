import React, { useState } from 'react';
import { FaRegStar, FaStar } from 'react-icons/fa';

function FavoriteTeamButton({ teamId, userId }) {
    const [isFavorite, setIsFavorite] = useState(false);

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

    return (
        <button onClick={handleClick}>
            {isFavorite ? <FaStar color={"#D3FC01"}/> : <FaRegStar color={"#D3FC01"}/>}
        </button>
    );
}

export default FavoriteTeamButton;
