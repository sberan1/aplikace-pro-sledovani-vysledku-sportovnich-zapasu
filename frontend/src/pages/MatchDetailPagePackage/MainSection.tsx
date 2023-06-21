import React, {MouseEventHandler, useEffect, useState} from 'react';
// @ts-ignore
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom';
import sparta from '../../assets/sparta.png';
import slavia from '../../assets/slavia.png';
import czechFlag from '../../assets/czechRepublicFlag.svg';
import './MatchDetailPage.css';
import BasketballDetailScore from "./DetailScoreComponents/BasketballDetailScore";
import {BasketballMatchData, FootballMatchData, HockeyMatchData, matchData} from "./SportInterfaces";
import HockeyDetailScore from "./DetailScoreComponents/HockeyDetailScore";
import FotballDetailScore from "./DetailScoreComponents/FotballDetailScore";
import VolleyballDetailScore from "./DetailScoreComponents/VolleyballDetailScore";
import FavouriteStar from "../../components/FavouriteStar/FavouriteStar";
import axios from "axios";
import { ProgressSpinner } from 'primereact/progressspinner';


const MainSection = ({MatchId} : { MatchId : number; }) => {


    const [match, setMatch] = useState<matchData>(
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
            score : {
                id: 1,
                finalAwayScore: 1,
                finalHomeScore: 2,
            },
            leagueName: "Liga mistrů",
            leagueFlag: czechFlag,
            favourite: false
}

    );
    const [loading, setLoading] = useState(true);

    const fetchMatches = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/fixture/getFixtureInfoById?id=${MatchId}`);
            setLoading(false);
            setMatch(response.data);
        } catch (error) {
            console.error('Error fetching match detaiů:', error);
        }
    };

    useEffect(() => {
        fetchMatches();
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
                case "Volleyball":
                scoreComponent = <VolleyballDetailScore MatchId={MatchId} />;
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


    if (!match) {
        return <ProgressSpinner />
    }

    return (
        <section className="MainSection">
            <div className={`MainSectionContainer flex justify-center`}>
                <div className="imgFotbalista"></div>
                <div className={`placeholder p-4 mx-10 grid gap-4 grid-rows-5 auto-rows-min mt-9 overflow-auto`}>
                    <div className={`row-span-2 grid grid-cols-11 auto-cols-min
                    /*flex justify-between auto-cols-min detailHeader px-10*/
                    `}>
                        <div className={`col-span-4 flex flex-col place-content-evenly`}>
                            <img className={`object-contain h-32`} src={match.homeTeamLogo} alt="Team" />
                            <button onClick={teamOnClicked(match.homeTeamName)} className={`teamButton`}>{match.homeTeamName}</button>
                        </div>
                        <div className={`col-span-3 flex flex-col justify-center px-5`}>
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
                                <div className={`country flex flex-row justify-center mt-6 py-1 px-3.5`}>
                                    <img className={`mr-2.5 object-contain h-7 w-7`} src={match.leagueFlag}/>
                                    <p className={`countryText self-center pt-0.5`}>{match.leagueName}</p>
                                </div>
                            </div>
                        </div>
                        <div className={` col-span-4 flex flex-row place-content-evenly`}>
                            <div className={`flex flex-col place-content-evenly`}>
                                <div className={`flex place-self-center`}>
                                    <img className={`object-contain h-32 w-40 fillSizeComponent`} src={match.awayTeamLogo} alt="Team" />
                                    <div className={`flex pl-8`}>
                                        <FavouriteStar Id={match.id} Type={"Match"} isFav={match.favourite}/>
                                    </div>
                                </div>
                                <button onClick={teamOnClicked(match.awayTeamName)} className={`teamButton`}>{match.awayTeamName}</button>
                            </div>
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
