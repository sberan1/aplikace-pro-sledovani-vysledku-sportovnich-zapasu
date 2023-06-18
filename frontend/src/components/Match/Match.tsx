import styles from './Match.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";

    function Match({id, date, time, homeTeamId, awayTeamId, homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, homeTeamLogo, awayTeamLogo} : {
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

        const teamOnClicked = (team: string): React.MouseEventHandler<HTMLButtonElement> => {
            return (event) => {
                // Obsluha kliknutí na tlačítko pro tým
            };
        };

        if((homeTeamScore === null || awayTeamScore === null) && (homeTeamLogo === null || awayTeamLogo === null)){
            return(
                <div className={`inline-grid grid-flow-col gap-3 ${styles.match} m-1`}>
                    <div className='columns-1 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col">
                        <div className='flex items-center mt-4 mb-1'>
                            <div className='flex grow w-60 justify-center'>
                                {/*<img src={homeTeamLogo} alt="Team" className={`content-center ${styles.imgTeam}`}></img>*/}
                                <button onClick={teamOnClicked(homeTeamName)} className={`pl-3 content-center ${styles.team}`}>{homeTeamName}</button>
                            </div>
                            <div className='flex grow-0'>
                                <p className={`${styles.dash}`}>-</p>
                            </div>
                            <div className='flex grow w-60 justify-center'>
                                <button onClick={teamOnClicked(awayTeamName)} className={`pr-3 content-center ${styles.team}`}>{awayTeamName}</button>
                                {/*<img src={awayTeamLogo} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>*/}
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>Nadcházející</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>Nadcházející</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex content-center items-center`}>
                        <div className={`inline-grid`}>
                            <ZobrazitVice className={`flex content-center`}></ZobrazitVice>
                        </div>

                    </div>
                </div>
            )
        }
        else if(homeTeamScore === null || awayTeamScore === null){
            return(
                <div className={`inline-grid grid-flow-col gap-3 ${styles.match} m-1`}>
                    <div className='columns-1 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col">
                        <div className='flex items-center mt-4 mb-1'>
                            <div className='flex grow w-60 justify-center'>
                                <img src={homeTeamLogo} alt="Team" className={`content-center ${styles.imgTeam}`}></img>
                                <button onClick={teamOnClicked(homeTeamName)} className={`pl-3 content-center ${styles.team}`}>{homeTeamName}</button>
                            </div>
                            <div className='flex grow-0'>
                                <p className={`${styles.dash}`}>-</p>
                            </div>
                            <div className='flex grow w-60 justify-center'>
                                <button onClick={teamOnClicked(awayTeamName)} className={`pr-3 content-center ${styles.team}`}>{awayTeamName}</button>
                                <img src={awayTeamLogo} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>Nadcházející</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>Nadcházející</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex content-center items-center`}>
                        <div className={`inline-grid`}>
                            <ZobrazitVice className={`flex content-center`}></ZobrazitVice>
                        </div>
                    </div>
                </div>
            )
        }
        else if((homeTeamScore !== null || awayTeamScore !== null) && (homeTeamLogo === null || awayTeamLogo === null)) {
            return (
                <div className={`inline-grid grid-flow-col gap-3 ${styles.match} m-1`}>
                    <div className='columns-1 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col">
                        <div className='flex items-center mt-4 mb-1'>
                            <div className='flex grow w-60 justify-center'>
                                {/*<img src={homeTeamLogo} alt="Team" className={`content-center ${styles.imgTeam}`}></img>*/}
                                <button onClick={teamOnClicked(homeTeamName)} className={`pl-3 content-center ${styles.team}`}>{homeTeamName}</button>
                            </div>
                            <div className='flex grow-0'>
                                <p className={`${styles.dash}`}>-</p>
                            </div>
                            <div className='flex grow w-60 justify-center'>
                                <button onClick={teamOnClicked(awayTeamName)} className={`pr-3 content-center ${styles.team}`}>{awayTeamName}</button>
                                {/*<img src={awayTeamLogo} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>*/}
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>{homeTeamScore}</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>{awayTeamScore}</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex content-center items-center`}>
                        <div className={`inline-grid`}>
                            <ZobrazitVice className={`flex content-center`}></ZobrazitVice>
                        </div>
                    </div>
                </div>
            )
        }
        else{
            return(
                <div className={`inline-grid grid-flow-col gap-3 ${styles.match} m-1`}>
                    <div className='columns-1 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col">
                        <div className='flex items-center mt-4 mb-1'>
                            <div className='flex grow w-60 justify-center'>
                                <img src={homeTeamLogo} alt="Team" className={`content-center ${styles.imgTeam}`}></img>
                                <button onClick={teamOnClicked(homeTeamName)} className={`pl-3 content-center ${styles.team}`}>{homeTeamName}</button>
                            </div>
                            <div className='flex grow-0'>
                                <p className={`${styles.dash}`}>-</p>
                            </div>
                            <div className='flex grow w-60 justify-center'>
                                <button onClick={teamOnClicked(awayTeamName)} className={`pr-3 content-center ${styles.team}`}>{awayTeamName}</button>
                                <img src={awayTeamLogo} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>{homeTeamScore}</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>{awayTeamScore}</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex content-center items-center`}>
                        <div className={`inline-grid`}>
                            <ZobrazitVice className={`flex content-center`}></ZobrazitVice>
                        </div>

                    </div>
                </div>
            )
        }

    }
    export default Match;