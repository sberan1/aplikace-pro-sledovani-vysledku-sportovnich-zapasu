import styles from './PrejitNaProfilTymu.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavnia.png';
import chevron from '../../../assets/chevron-right.svg';

function PrejitNaProfilTymu({ handleClick }: { handleClick: () => void }) {
    return (
        <div className={`flex items-center ${styles.text}`}>
            <button onClick={handleClick} className={`${styles.text} uppercase`}>Přejít na profil týmu
                <div>
                    <svg width="10" height="16" fill="black" className={`${styles.icon}`}
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="M10 8L1.51943 16L0 14.5667L6.99647 8L0.0353357 1.43333L1.55477 -1.66893e-06L10 8Z"/>
                    </svg>
                </div>
            </button>

        </div>
    )
}

export default PrejitNaProfilTymu;