import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import './Navbar.css';
import './MatchDetailPage.css';
import ContentHolder from "../BrowsingContentHolder/ContentHolder";
import styles from "../Match/Match.module.css";

interface BasketballMatchData {
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamScore: number | null,
    awayTeamScore: number | null,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,


    id: number,
    finalAwayScore: number,
    finalHomeScore: number,
    firstQuarterAwayScore: number,
    firstQuarterHomeScore: number,
    secondQuarterAwayScore: number,
    secondQuarterHomeScore: number,
    thirdQuarterAwayScore: number,
    thirdQuarterHomeScore: number,
    fourthQuarterAwayScore: number,
    fourthQuarterHomeScore: number,
    overtimeAwayScore: number,
    overtimeHomeScore: number
}

interface HockeyMatchData {
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamScore: number | null,
    awayTeamScore: number | null,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,

    id: number,
    finalAwayScore: number,
    finalHomeScore: number,
    firstPeriodAwayScore: number,
    firstPeriodHomeScore: number,
     secondPeriodAwayScore : number,
     secondPeriodHomeScore : number,
     thirdPeriodAwayScore : number,
     thirdPeriodHomeScore : number,
     overtimeAwayScore : number,
     overtimeHomeScore : number,
     shootoutAwayScore : number,
     shootoutHomeScore : number
}

interface FootballMatchData {
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamScore: number | null,
    awayTeamScore: number | null,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,

     id : number,
     finalAwayScore : number,
     finalHomeScore: number,
     firstHalfAwayScore : number,
     firstHalfHomeScore : number,
     secondHalfAwayScore  : number,
     secondHalfHomeScore : number,
     overtimeAwayScore : number,
     overtimeHomeScore : number,
     penaltyAwayScore : number,
     penaltyHomeScore : number
}


const MainSection = ({MatchId, SportType} : { MatchId : number; SportType : string;}) => {


    const [match, setMatch] = useState<BasketballMatchData | HockeyMatchData | FootballMatchData | null>(null);

    const fetchMatches = async () => {
        try {
            const response = await fetch(`http://localhost:8080/`);
            const data = await response.json();
            setMatch(data);
        } catch (error) {
            console.error('Error fetching match detaiů:', error);
        }
    };

    useEffect(() => {

    }, []);




    const teamOnClicked = (teamName : string) : MouseEventHandler<HTMLButtonElement> => {
        return undefined;
    }

    return (
        <section className="MainSection">
            <div className={`MainSectionContainer flex justify-center`}>
                <div className="imgFotbalista"></div>




                <div className={`placeholder p-4 m-10 grid gap-4 grid-rows-2`}>
                    <div className={`inline-grid grid-cols-3 gap-4`}>
                        <div>
                            <img className={`content-center object-contain h-15 w-15`} src={"____"} alt="Team" />
                            <button onClick={teamOnClicked(match.homeTeamName)} className={`pl-3 content-center ${styles.team}`}>{match.homeTeamName}</button>
                        </div>
                        <div>

                        </div>
                        <div>

                        </div>
                    </div>
                    <div className={``}>

                    </div>

                </div>




            </div>
        </section>
    );
};

export default MainSection;
