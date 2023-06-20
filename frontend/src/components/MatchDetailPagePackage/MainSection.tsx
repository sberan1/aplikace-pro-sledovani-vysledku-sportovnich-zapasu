import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom';
import './Navbar.css';
import './MatchDetailPage.css';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import czechFlag from '../../assets/czechRepublicFlag.svg';
import BasketballDetailScore from "./DetailScoreComponents/BasketballDetailScore";
import {BasketballMatchData, FootballMatchData, HockeyMatchData} from "./SportInterfaces";
import HockeyDetailScore from "./DetailScoreComponents/HockeyDetailScore";
import FotballDetailScore from "./DetailScoreComponents/FotballDetailScore";


const MainSection = ({MatchId} : { MatchId : number; }) => {


    const [match, setMatch] = useState<BasketballMatchData | HockeyMatchData | FootballMatchData>(
        {
            id: 1,
            sport: "Basketball",
            date: "2021-05-05",
            time: "20:00",
            homeTeamId: 1,
            awayTeamId: 2,
            homeTeamName: "FC Barcelona",
            awayTeamName: "Real Madrid",
            homeTeamLogo: sparta,
            awayTeamLogo: slavia,
            alreadyPlayed: false,
            isFavourite: false,
            score : {
                finalAwayScore: 1,
                finalHomeScore: 2,
                firstQuarterAwayScore: 1,
                firstQuarterHomeScore: 1,
                secondQuarterAwayScore: 0,
                secondQuarterHomeScore: 1,
                thirdQuarterAwayScore: 0,
                thirdQuarterHomeScore: 0,
                fourthQuarterAwayScore: 0,
                fourthQuarterHomeScore: 0,
                overtimeAwayScore: 0,
                overtimeHomeScore: 0,
            },
            leagueName: "Liga mistrů",
            leagueFlag: czechFlag
        }
    );

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

    const returnScoreComponent = () => {
        let scoreComponent : JSX.Element = <div></div>;

        switch (match.sport) {
            case "Basketball":
                scoreComponent = <BasketballDetailScore MatchId={MatchId} />;
                break;
            case "Football":
                scoreComponent = <FotballDetailScore MatchId={MatchId} />;
                break;
            case "Hockey":
                scoreComponent = <HockeyDetailScore MatchId={MatchId} />;
                break;
        }

        return scoreComponent;
    }

    let isVisibleHomeTeamLogo = false;
    let isVisibleAwayTeamLogo = false;
    let isVisibleHomeTeamScore = false;
    let isVisibleAwayTeamScore = false;
    let isFutureMatch = false;

    const isVisibleMap = () => {
        if (match.homeTeamLogo !== null) {
            isVisibleHomeTeamLogo = true;
        }
        if (match.awayTeamLogo !== null) {
            isVisibleAwayTeamLogo = true;
        }
        if (match.score.finalHomeScore !== null) {
            isVisibleHomeTeamScore = true;
        }
        if (match.score.finalAwayScore !== null) {
            isVisibleAwayTeamScore = true;
        }
    }

    isVisibleMap();


    const teamOnClicked = (teamName : string) : MouseEventHandler<HTMLButtonElement> => {
        return undefined;
    }

    return (
        <section className="MainSection">
            <div className={`MainSectionContainer flex justify-center`}>
                <div className="imgFotbalista"></div>
                <div className={`placeholder p-4 mx-10 grid gap-4 grid-rows-5 auto-rows-min`}>
                    <div className={`row-span-2 flex justify-around auto-cols-min detailHeader {/*px-20*/}`}>
                        <div className={`flex flex-col place-content-evenly`}>
                            <img className={`object-contain h-32`} src={match.homeTeamLogo} alt="Team" />
                            <button onClick={teamOnClicked(match.homeTeamName)} className={`teamButton`}>{match.homeTeamName}</button>
                        </div>
                        <div className={`flex flex-col justify-center`}>
                            <div className={`scorePlaceholder flex justify-center px-12 pt-0.5 mt-8`}>
                                {isFutureMatch ? (
                                            <p className={`scorePlaceholderText pt-0.5 pb-0.2`}>Nadcházející</p>
                                ) : (
                                    <>
                                        {isVisibleHomeTeamScore ? (
                                            <p className={`scorePlaceholderText pr-4 pt-0.5 pb-0.2`}>{match.score.finalHomeScore}</p>
                                        ) : (
                                            <div />
                                        )}
                                            <p className={`scorePlaceholderText  pt-0.5 pb-0.3`}>:</p>
                                        {isVisibleAwayTeamScore ? (
                                            <p className={`scorePlaceholderText pl-4 pt-0.5 pb-0.2`}>{match.score.finalAwayScore}</p>
                                        ) : (
                                            <div />
                                        )}
                                    </>
                                )}
                            </div>
                            <div className={`flex justify-center`}>
                                <div className={`country flex flex-row justify-center mt-6 py-1.5 px-4`}>
                                    <img className={`flag mr-4`} src={match.leagueFlag}/>
                                    <p className={`countryText`}>{match.leagueName}</p>
                                </div>
                            </div>

                        </div>
                        <div className={`flex flex-col place-content-evenly`}>
                            <img className={`object-contain h-32`} src={match.awayTeamLogo} alt="Team" />
                            <button onClick={teamOnClicked(match.homeTeamName)} className={`teamButton`}>{match.homeTeamName}</button>
                        </div>
                    </div>
                    <div className={`row-span-3 scrollContainer`}>
                        {returnScoreComponent()}
                    </div>

                </div>






            </div>
        </section>
    );
};

export default MainSection;
