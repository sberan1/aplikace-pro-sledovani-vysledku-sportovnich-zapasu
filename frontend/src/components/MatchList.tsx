import React, { useEffect, useState } from 'react';

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
                    const response = await fetch('http://localhost:8080/match/getMatchesFromLeague?' + item);
                    const data = await response.json();
                    setMatches(data);
                } catch (error) {
                    console.error('Error fetching leagues:', error);
                }
            };

            fetchMatches();
        }, []);
    }

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

    let matchList : match[] = [];

    matches.map(individualMatch => (
        matchList.push(individualMatch.id, individualMatch.date,individualMatch.time,individualMatch.team1,individualMatch.team2,individualMatch.score1,individualMatch.score2,individualMatch.imgSource1,individualMatch.imgSource2)
    ));

    return matchList;
};

export default MatchList;
