type MatchType = {
    id: number;
    date: any;
    time: any;
    homeTeam: string;
    awayTeam: string;
    homeTeamScore: number;
    awayTeamScore: number;
    homeTeamLogo: string;
    awayTeamLogo: string;
}

export { MatchType };

type LeagueType = {
    id: number,
    name: string,
    flagSource: string
}

export { LeagueType };