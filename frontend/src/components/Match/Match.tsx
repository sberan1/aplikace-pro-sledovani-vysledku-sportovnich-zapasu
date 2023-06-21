import styles from './Match.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";
import {isVisible} from "@testing-library/user-event/dist/utils";
import {useState} from "react";

function Match({
                   id,
                   date,
                   time,
                   homeTeamId,
                   awayTeamId,
                   homeTeamName,
                   awayTeamName,
                   homeTeamScore,
                   awayTeamScore,
                   homeTeamLogo,
                   awayTeamLogo
               }: {
    id: any,
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamScore: number | null,
    awayTeamScore: number | null,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null
}) {


    let isVisibleHomeTeamLogo = false;
    let isVisibleAwayTeamLogo = false;
    let isVisibleHomeTeamScore = false;
    let isVisibleAwayTeamScore = false;
    let isFutureMatch = false;

    const isVisibleMap = () => {
        if (homeTeamLogo !== null) {
            isVisibleHomeTeamLogo = true;
        }
        if (awayTeamLogo !== null) {
            isVisibleAwayTeamLogo = true;
        }
        if (homeTeamScore !== null) {
            isVisibleHomeTeamScore = true;
        }
        if (awayTeamScore !== null) {
            isVisibleAwayTeamScore = true;
        }
        if (homeTeamScore === null && awayTeamScore === null) {
            isFutureMatch = true;
        }
    }

    isVisibleMap();


    const teamOnClicked = (team : number): React.MouseEventHandler<HTMLButtonElement> => {
        return (event) => {
            const nextURL = `/teamDetail?teamId=${id}`; // URL s parametrem
            window.location.href = nextURL;
        };
    };

    const formateDateToCzech = date.split('-').reverse().join('.');

    const zobrazitViceOnClick = () => {
        const nextURL = `/matchDetail?id=${id}`; // URL s parametrem
        window.location.href = nextURL;
    }

    return (
        <div className={`inline-grid grid-flow-col gap-3 ${styles.match} m-1`}>
            <div className='columns-1 inline-grid grid-rows-2 pl-6'>
                <div className='flex items-end'>
                    <p className={`${styles.text1Thin} `}>{formateDateToCzech}</p>
                </div>
                <div className='items-start'>
                    <p className={`${styles.text1Bold}`}>{time}</p>
                </div>
            </div>
            <div className="columns-1 grid-flow-col">
                <div className='flex items-center mt-4 mb-1'>
                    <div className='flex grow w-60 justify-center'>
                        {isVisibleHomeTeamLogo ? (
                            <>
                                <img className={`content-center object-contain h-9 w-9`} src={homeTeamLogo} alt="Team" />
                                <button onClick={teamOnClicked(homeTeamId)} className={`pl-3 content-center ${styles.team}`}>{homeTeamName}</button>
                            </>
                        ) : (
                            <div />
                        )}
                    </div>
                    <div className='flex grow-0'>
                        <p className={`${styles.dash}`}>-</p>
                    </div>
                    <div className='flex grow w-60 justify-center'>
                        <button onClick={teamOnClicked(homeTeamId)} className={`pr-3 content-center ${styles.team}`}>{awayTeamName}</button>
                        {isVisibleAwayTeamLogo ? (
                            <img className={`content-center object-contain h-9 w-9`} src={awayTeamLogo} alt="Team" />
                        ) : (
                            <div/>
                        )}
                    </div>
                </div>
                <div className={`flex justify-center mb-3`}>
                    <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                        {isFutureMatch ? (
                            <p className={`${styles.score1} pt-0.5 pb-0.2`}>Nadcházející</p>
                        ) : (
                            <>
                                {isVisibleHomeTeamScore ? (
                                    <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>{homeTeamScore}</p>
                                ) : (
                                    <div/>
                                )}
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                {isVisibleAwayTeamScore ? (
                                    <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>{awayTeamScore}</p>
                                ) : (
                                    <div/>
                                )}
                            </>
                        )}
                    </div>
                </div>
            </div>
            <div className={`flex justify-end pr-6`}>
                <div className={`inline-grid`}>
                    <ZobrazitVice handleClick={zobrazitViceOnClick} />
                </div>
            </div>
        </div>
    );

}
export default Match;