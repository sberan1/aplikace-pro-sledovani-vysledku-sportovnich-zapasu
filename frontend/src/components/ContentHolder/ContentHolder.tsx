import styles from './ContentHolder.module.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import ZobrazitVice from "../Buttons/ZobrazitVice/ZobrazitVice";
import MatchList from "./../MatchList";
import Match from "../Match/Match";
import { useState, useEffect, useRef } from "react";
import {MatchType} from "../Types";
import LeagueList from "../LeagueList";
import League from "../League/League";
import leagueList from "../LeagueList";

function ContentHolder({ sport }: {
    sport: string;
})
{

    return (
        <div>
            <div>
                <h2 className={`${styles.sportName} p-4`}>{sport}</h2>
            </div>
            <div>
                <LeagueList date={"2023-03-19"} sport={sport} />
            </div>
        </div>
    )
}

export default ContentHolder;
