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
        <div className={`flex ${styles.itemPlaceHolder} place-content-between px-7 mt-2`}>
            <div className={`flex place-items-center`}>
                <img src={teamLogo} className={`object-contain h-10 w-10`}/>
                <p className={`${styles.teamText} px-5`}>{teamName}</p>
            </div>
            <div className={`flex place-items-center pt-1`}>
                <PrejitNaProfilTymu handleClick={teamOnClicked}/>
            </div>
        </div>
    );

}
export default SearchItem;