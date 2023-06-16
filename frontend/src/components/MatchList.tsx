import React, { useEffect, useState } from 'react';
import {MatchType} from "./Types";

enum Type { Team, League }

const MatchList = (type : Type, item : String) => {
    const [matches, setMatches] = useState([]);

    if(type = Type.Team){
        useEffect(() => {
            const fetchMatches = async () => {
                try {
                    const response = await fetch('http://localhost:8080/match/getMatchesFromTeam?'+item);
                    const data = await response.json();
                    setMatches(data);
                } catch (error) {
                    console.error('Error fetching leagues:', error);
                }
            };

            fetchMatches();
        }, []);
    }
    if(type = Type.League) {
        useEffect(() => {
            const fetchMatches = async () => {
                try {
                    const response = await fetch('http://localhost:8080/fixture/getFixturesBySportAndDate' + item);
                    const data = await response.json();
                    setMatches(data);
                } catch (error) {
                    console.error('Error fetching leagues:', error);
                }
            };

            fetchMatches();
        }, []);
    }



    let matchList : Array<MatchType> = [];

    for (let i = 0; i < matches.length; i++) {
        matchList.push(matches[i].id, matches[i].date,matches[i].time,matches[i].team1,matches[i].team2,matches[i].score1,matches[i].score2,matches[i].imgSource1,matches[i].imgSource2);
    }


    return matchList;
};

export default MatchList;
