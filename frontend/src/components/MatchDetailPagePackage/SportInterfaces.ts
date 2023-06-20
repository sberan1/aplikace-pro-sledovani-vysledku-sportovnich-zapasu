export interface BasketballMatchData {
    id: number,
    sport: string,
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,
    alreadyPlayed: boolean,
    isFavourite: boolean,
    score: {
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
    },
    leagueName: string,
    leagueFlag: string

}

export interface HockeyMatchData {
    id: number,
    sport: string,
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,
    alreadyPlayed: boolean,
    isFavourite: boolean,
    score: {
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
    },
    leagueName: string,
    leagueFlag: string
}

export interface FootballMatchData {
    id: number,
    sport: string,
    date: any,
    time: any,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    homeTeamLogo: string | null,
    awayTeamLogo: string | null,
    alreadyPlayed: boolean,
    isFavourite: boolean,
    score: {
        finalAwayScore: number,
        finalHomeScore: number,
        firstHalfAwayScore: number,
        firstHalfHomeScore: number,
        secondHalfAwayScore: number,
        secondHalfHomeScore: number,
        overtimeAwayScore: number,
        overtimeHomeScore: number,
        penaltyAwayScore: number,
        penaltyHomeScore: number
    },
    leagueName: string,
    leagueFlag: string
}