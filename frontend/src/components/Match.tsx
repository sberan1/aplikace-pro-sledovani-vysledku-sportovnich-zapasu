import styles from './Match.module.css';
import sparta from '../assets/sparta.png';
import slavia from '../assets/slavia.png';

function Match(){
    return(
        <div className={styles.match}>
            <p className={styles.text1Thin}>13.04.2023</p>
            <p className={styles.text1Bold}>16:15</p>
            <img src={sparta} alt="Team" className={styles.imgTeam}></img>
            <img src={slavia} alt="Team" className={styles.imgTeam2}></img>
            <p className={styles.team1}>Sparta</p>
            <p className={styles.team2}>Slavia</p>
            <p className={styles.dash}>-</p>
            <div className={styles.score}></div>
            <p className={styles.score1}>1</p>
            <p className={styles.colon}>:</p>
            <p className={styles.score2}>2</p>
        </div>
    )
}

export default Match;