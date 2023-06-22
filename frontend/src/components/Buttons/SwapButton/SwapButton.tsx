import styles from './SwapButton.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavnia.png';
import chevron from '../../../assets/chevron-right.svg';
import options from "../../BodyTymu/BodyTymu";
import {useEffect, useState} from "react";


function SwapButton({ handleClick, state }: { handleClick: () => void , state: string }) {

    const [localOptions, setOptions] = useState(['PROGRAM', 'VÝSLEDKY']);


    return (
        <div className={`flex justify-between ${styles.placeHolder}`}>
            {state === localOptions[0] ? (
                <div className={`isOn flex`}>
                    <button
                        onClick={handleClick}
                        className={`${styles.programOn} uppercase`}
                    >
                        Program
                    </button>
                    <button
                        onClick={handleClick}
                        className={`${styles.vysledkyOff} uppercase`}
                    >
                        Výsledky
                    </button>
                </div>
            ) : (
                <div className={`isOff flex`}>
                    <button
                        onClick={handleClick}
                        className={`${styles.programOff} uppercase`}
                    >
                        Program
                    </button>
                    <button
                        onClick={handleClick}
                        className={`${styles.vysledkyOn} uppercase`}
                    >
                        Výsledky
                    </button>
                </div>
            )}
        </div>
    );
}

export { SwapButton };