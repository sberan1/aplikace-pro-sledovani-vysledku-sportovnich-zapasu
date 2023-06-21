export abstract class matchData {
    id: number;
    sport: string;
    date: any;
    time: any;
    homeTeamId: number;
    awayTeamId: number;
    homeTeamName: string;
    awayTeamName: string;
    homeTeamLogo: string | null;
    awayTeamLogo: string | null;
    score: Score;
    leagueName: string;
    leagueFlag: string;
    favourite: boolean;
}

export interface VolleyballMatchData extends matchData{
    alreadyPlayed: boolean,
    favourite: boolean,
    score: VolleyballScore,
    leagueName: string,
    leagueFlag: string

}

export interface BasketballMatchData extends matchData{
    alreadyPlayed: boolean,
    favourite: boolean,
    score: BasketballScore,
    leagueName: string,
    leagueFlag: string
}

export interface HockeyMatchData extends matchData{
    alreadyPlayed: boolean,
    favourite: boolean,
    score: HockeyScore,
    leagueName: string,
    leagueFlag: string
}

export interface FootballMatchData extends matchData{
    alreadyPlayed: boolean,
    favourite: boolean,
    score: FootballScore,
}

export abstract class Score {
    id: number;
    finalAwayScore: number;
    finalHomeScore: number;
}

export interface FootballScore extends Score {
    firstHalfAwayScore: number,
    firstHalfHomeScore: number,
    secondHalfAwayScore: number,
    secondHalfHomeScore: number,
    overtimeAwayScore: number,
    overtimeHomeScore: number,
    penaltyAwayScore: number,
    penaltyHomeScore: number
}

export interface HockeyScore extends Score {
    id: number,
    finalAwayScore: number,
    finalHomeScore: number,
    firstPeriodAwayScore: number,
    firstPeriodHomeScore: number,
    secondPeriodAwayScore: number,
    secondPeriodHomeScore: number,
    thirdPeriodAwayScore: number,
    thirdPeriodHomeScore: number,
    overtimeAwayScore: number,
    overtimeHomeScore: number,
    shootoutAwayScore: number,
    shootoutHomeScore: number
}

export interface BasketballScore extends Score {
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
export interface VolleyballScore extends Score {
    id: number,
    finalAwayScore: number,
    finalHomeScore: number,
    firstSetAwayScore: number,
    firstSetHomeScore: number,
    secondSetAwayScore: number,
    secondSetHomeScore: number,
    thirdSetAwayScore: number,
    thirdSetHomeScore: number,
    fourthSetAwayScore: number,
    fourthSetHomeScore: number,
    fifthSetAwayScore: number,
    fifthSetHomeScore: number

}