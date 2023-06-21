import styles from './SearchItem.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";
import {isVisible} from "@testing-library/user-event/dist/utils";
import {useState} from "react";
import {useLocation} from 'react-router-dom';
import PrejitNaProfilTymu from "../Buttons/PrejitNaProfilTymu/PrejitNaProfilTymu";

function SearchItem({teamId, teamName, teamLogo} : {
    teamId: number,
    teamName: string,
    teamLogo: string
}) {


    const teamOnClicked = () => {
        const nextURL = `/teamDetail?teamId=${teamId}`; // URL s parametrem
        window.location.href = nextURL;
    };


    return (
        <div className={`flex ${styles.itemPlaceHolder}`}>
            <img src={teamLogo} className={``}/>
            <p>{teamName}</p>
            <PrejitNaProfilTymu handleClick={teamOnClicked}/>
        </div>
    );

}
export default SearchItem;