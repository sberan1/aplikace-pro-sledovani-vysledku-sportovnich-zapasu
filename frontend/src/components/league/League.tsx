import styles from './League.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../buttons/zobrazitVice/ZobrazitVice";
import MatchList from "./../MatchList";
import Match from "../match/Match";

    type match = {
        id: bigint,
        date: any,
        time: any,
        team1: string,
        team2: string,
        score1: number
        score2: number,
        imgSource1: string,
        imgSource2: string
    }

    function League({id, name, matchList} : {
        id: bigint,
        name: string,
        matchList: match[]
    }) {

        return (
            <div className={styles.league}>
                <div className={`${styles.leagueHeader} flex`}>
                    <h2 className={styles.leagueName}>{name}</h2>
                    <button></button>
                </div>
                <div className={styles.leagueMatches}>
                    {matchList.map(match => (
                        <Match id={match.id}
                               date={match.date}
                               time={match.time}
                               team1={match.team1}
                               team2={match.team2}
                               score1={match.score1}
                               score2={match.score2}
                               imgSource1={match.imgSource1}
                               imgSource2={match.imgSource2}>
                        </Match>
                    ))}

                </div>
                <div className={styles.leagueButton}>

                </div>
            </div>
        );





    }
    export default League;