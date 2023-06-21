import styles from './MatchDetailedScore.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";
import {isVisible} from "@testing-library/user-event/dist/utils";
import {useState} from "react";
import {useLocation} from 'react-router-dom';

function MatchDetailedScore({text, homeScore, awayScore} : {
    text: string;
    homeScore: number | null;
    awayScore: number | null;
}) {

    const location = useLocation();

    let isVisibleHomeScore = false;
    let isVisibleAwayScore = false;

    const isVisibleMap = () => {
        if (homeScore !== null) {
            isVisibleHomeScore = true;
        }
        if (awayScore !== null) {
            isVisibleAwayScore = true;
        }
    }

    isVisibleMap();

    return (
        <div className={`inline-grid gap-3 ${styles.match} mb-3`}>
            <div className="columns-1 shrink">
                <div className={`flex justify-center align-items-middle shrink mt-2 mb-1.5 p-1 `}>
                    <p className={`${styles.textBorder} pb-1 pt-1.5 px-3.5`}>{text}</p>
                </div>
                <div className={`flex justify-center mb-3`}>
                    <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                        {isVisibleHomeScore ? (
                            <p className={`${styles.score1} pr-4 pt-0.4 pb-0.2`}>{homeScore}</p>
                        ) : (
                            <div />
                        )}
                        <p className={`${styles.colon} pt-0.3 pb-0.3`}>:</p>
                        {isVisibleAwayScore ? (
                            <p className={`${styles.score2} pl-4 pt-0.4 pb-0.2`}>{awayScore}</p>
                        ) : (
                            <div />
                        )}
                    </div>
                </div>
            </div>

        </div>
    );

}
export default MatchDetailedScore;