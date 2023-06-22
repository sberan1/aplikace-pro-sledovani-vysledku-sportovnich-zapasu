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
import DatePicker from "../DatePicker/DatePicker";
import { parse, format } from 'date-fns';

function ContentHolder({ sport }: {
    sport: string;
})
{
    const { formattedDateToReturn, render } = DatePicker();

    return (
        <div className={`${styles.placeHolder} p-4 grid grid-flow-row auto-rows-max overflow-auto mt-10 ml-80`}>
            <div className={`flex justify-between`}>
                <h2 className={`${styles.sportName} pl-10 pt-10 pb-11`}>{sport}</h2>
                <div className={`pr-10 pt-10 pb-11`}>
                    {render}
                </div>
            </div>
            <div className={`grid grid-flow-row auto-rows-max place-items-center`}>
                <LeagueList date={formattedDateToReturn} sport={sport} />
            </div>
        </div>
    )
}

export default ContentHolder;
