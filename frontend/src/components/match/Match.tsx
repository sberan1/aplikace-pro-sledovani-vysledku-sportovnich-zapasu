import styles from './Match.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../buttons/zobrazitVice/ZobrazitVice";

    function Match({id, date, time, team1, team2, score1, score2, imgSource1, imgSource2} : {
        id: any,
        date: any,
        time: any,
        team1: string,
        team2: string,
        score1: number | null,
        score2: number | null,
        imgSource1: string | null,
        imgSource2: string | null
    }) {

        const teamOnClicked = (team: string): React.MouseEventHandler<HTMLButtonElement> => {
            return (event) => {
                // Obsluha kliknutí na tlačítko pro tým
            };
        };

        if((score1 === null || score2 === null) && (imgSource1 === null || imgSource2 === null)){
            return(
                <div className={`inline-grid grid-flow-col auto-cols-max gap-4 ${styles.match} m-1`}>
                    <div className='columns-1 flex-initial w-64 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin}`}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col auto-cols-max">
                        <div className='inline-grid grid-cols-3'>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <div  className={styles.imgTeam}></div>
                                <button onClick={teamOnClicked(team1)} className={`pl-3 content-center ${styles.team}`}>{team1}</button>
                            </div>
                            <div className='grid justify-items-center items-center'>
                                <p className={`${styles.dash} pt-3`}>-</p>
                            </div>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <button onClick={teamOnClicked(team2)} className={`pl-3 content-center ${styles.team}`}>{team2}</button>
                                <div className={styles.imgTeam}></div>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pt-0.5 pb-0.2`}>Nadcházející</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex pl-10`}>
                        <ZobrazitVice className={`justify-self-end`}></ZobrazitVice>
                    </div>
                </div>
            )
        }
        else if(score1 === null || score2 === null){
            return(
                <div className={`inline-grid grid-flow-col auto-cols-max gap-4 ${styles.match} m-1`}>
                    <div className='columns-1 flex-initial w-64 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col auto-cols-max">
                        <div className='inline-grid grid-cols-3'>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <img src={imgSource1} alt="Team" className={`content-center ${styles.imgTeam}`}></img>
                                <button onClick={teamOnClicked(team1)} className={`pl-3 content-center ${styles.team}`}>{team1}</button>
                            </div>
                            <div className='grid justify-items-center items-center'>
                                <p className={`${styles.dash} pt-3`}>-</p>
                            </div>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <img src={imgSource2} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>
                                <button onClick={teamOnClicked(team2)} className={`pl-3 content-center ${styles.team}`}>{team2}</button>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pt-0.5 pb-0.2`}>Nadcházející</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex pl-10`}>
                        <ZobrazitVice className={`justify-self-end`}></ZobrazitVice>
                    </div>
                </div>
            )
        }
        else if((score1 !== null || score2 !== null) && (imgSource1 === null || imgSource2 === null)) {
            return (
                <div className={`inline-grid grid-flow-col auto-cols-max gap-4 ${styles.match} m-1`}>
                    <div className='columns-1 flex-initial w-64 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col auto-cols-max">
                        <div className='inline-grid grid-cols-3'>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <div className={styles.imgTeam}></div>
                                <button onClick={teamOnClicked(team1)} className={`pl-3 content-center ${styles.team}`}>{team1}</button>
                            </div>
                            <div className='grid justify-items-center items-center'>
                                <p className={`${styles.dash} pt-3`}>-</p>
                            </div>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <button onClick={teamOnClicked(team2)} className={`pl-3 content-center ${styles.team}`}>{team2}</button>
                                <div className={styles.imgTeam}></div>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>{score1}</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>{score2}</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex pl-10`}>
                        <ZobrazitVice className={`justify-self-end`}></ZobrazitVice>
                    </div>
                </div>
            )
        }
        else{
            return(
                <div className={`inline-grid grid-flow-col auto-cols-max gap-4 ${styles.match} m-1`}>
                    <div className='columns-1 flex-initial w-64 inline-grid grid-rows-2 pl-6'>
                        <div className='flex items-end'>
                            <p className={`${styles.text1Thin} `}>{date}</p>
                        </div>
                        <div className='items-start'>
                            <p className={`${styles.text1Bold}`}>{time}</p>
                        </div>
                    </div>
                    <div className="columns-1 grid-flow-col auto-cols-max">
                        <div className='inline-grid grid-cols-3'>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <img src={imgSource1} alt="Team" className={`content-center ${styles.imgTeam}`}></img>
                                <button onClick={teamOnClicked(team1)} className={`pl-3 content-center ${styles.team}`}>{team1}</button>
                            </div>
                            <div className='grid justify-items-center items-center'>
                                <p className={`${styles.dash} pt-3`}>-</p>
                            </div>
                            <div className='grid-flow-col pr-3 pl-3 pt-3 flex items-center'>
                                <img src={imgSource2} alt="Team" className={`content-center ${styles.imgTeam2}`}></img>
                                <button onClick={teamOnClicked(team2)} className={`pl-3 content-center ${styles.team}`}>{team2}</button>
                            </div>
                        </div>
                        <div className={`flex justify-center mb-3`}>
                            <div className={`${styles.score} flex justify-center px-4 pt-0.5`}>
                                <p className={`${styles.score1} pr-4 pt-0.5 pb-0.2`}>{score1}</p>
                                <p className={`${styles.colon}  pt-0.5 pb-0.3`}>:</p>
                                <p className={`${styles.score2} pl-4 pt-0.5 pb-0.2`}>{score2}</p>
                            </div>
                        </div>
                    </div>
                    <div className={`flex pl-10`}>
                            <ZobrazitVice className={`justify-self-end`}></ZobrazitVice>
                    </div>
                </div>
            )
        }

    }
    export default Match;