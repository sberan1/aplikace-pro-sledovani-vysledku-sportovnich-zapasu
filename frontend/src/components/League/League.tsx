import styles from './League.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";
import MatchList from "./../MatchList";
import Match from "../Match/Match";
import { useState, useEffect, useRef } from "react";
import {MatchType} from "../Types";

function League({ id, name, flagSource, matchList }: {
    id: any;
    name: string;
    flagSource: string;
    matchList: Array<JSX.Element>;
}) {
    const [open, setOpen] = useState(false);

    const toggle = () => {
        setOpen(!open);
    };

    return (
        <div className={`${styles.league} /*inline-grid*/ py-4`} style={{ height: open ? 'fit-content' : '60px' }}>
            <div className={`${styles.leagueHeader} uppercase px-6 pb-4 flex justify-between`}>
                <div className={`flex items-center`}>
                    <div>
                        <img className={`${styles.flag} mr-4`} src={flagSource}/>
                    </div>
                    <div>
                        <h2 className={`${styles.leagueName} pt-1`}>{name}</h2>
                    </div>
                </div>
                <button onClick={toggle}>
                    {open && (
                        <svg className={styles.button} width="17" height="10" viewBox="0 0 17 10" xmlns="http://www.w3.org/2000/svg">
                            <path d="M8.09524 1.33514e-05L0 7.9275L1.4504 9.34784L8.09524 2.80766L14.7401 9.31481L16.1905 7.89447L8.09524 1.33514e-05Z"/>
                        </svg>
                    )}
                    {!open && (
                        <svg className={styles.button} width="17" height="11" viewBox="0 0 17 11" xmlns="http://www.w3.org/2000/svg">
                            <path d="M8.19045 10.0435L0.0952148 2.11602L1.54561 0.695679L8.19045 7.23585L14.8353 0.72871L16.2857 2.14905L8.19045 10.0435Z"/>
                        </svg>
                    )}
                </button>
            </div>
            {open && (
                <div className={`${styles.leagueMatches} px-3 inline-grid justify-items-center justify-center`}>
                    {
                        matchList.map(item => (
                            item
                        ))
                    }
                </div>
            )}
        </div>
    );
}

export default League;
