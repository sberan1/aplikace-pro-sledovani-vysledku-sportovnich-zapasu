CREATE TABLE basketball_score
(
    id                        BIGINT NOT NULL,
    final_away_score          INT    NOT NULL,
    final_home_score          INT    NOT NULL,
    first_quarter_away_score  INT    NOT NULL,
    first_quarter_home_score  INT    NOT NULL,
    second_quarter_away_score INT    NOT NULL,
    second_quarter_home_score INT    NOT NULL,
    third_quarter_away_score  INT    NOT NULL,
    third_quarter_home_score  INT    NOT NULL,
    fourth_quarter_away_score INT    NOT NULL,
    fourth_quarter_home_score INT    NOT NULL,
    overtime_away_score       INT    NOT NULL,
    overtime_home_score       INT    NOT NULL,
    CONSTRAINT pk_basketballscore PRIMARY KEY (id)
);

CREATE TABLE hockey_score
(
    id                       BIGINT NOT NULL,
    final_away_score         INT    NOT NULL,
    final_home_score         INT    NOT NULL,
    first_period_away_score  INT    NOT NULL,
    first_period_home_score  INT    NOT NULL,
    second_period_away_score INT    NOT NULL,
    second_period_home_score INT    NOT NULL,
    third_period_away_score  INT    NOT NULL,
    third_period_home_score  INT    NOT NULL,
    overtime_away_score      INT    NOT NULL,
    overtime_home_score      INT    NOT NULL,
    shootout_away_score      INT    NOT NULL,
    shootout_home_score      INT    NOT NULL,
    CONSTRAINT pk_hockeyscore PRIMARY KEY (id)
);

CREATE TABLE league
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NULL,
    type        VARCHAR(255) NULL,
    season      VARCHAR(255) NULL,
    external_id INT          NOT NULL,
    CONSTRAINT pk_league PRIMARY KEY (id)
);

CREATE TABLE `match`
(
    id           BIGINT       NOT NULL,
    sport        VARCHAR(255) NULL,
    home_team_id BIGINT       NULL,
    away_team_id BIGINT       NULL,
    date         datetime     NULL,
    vlajka       BLOB         NULL,
    country      VARCHAR(255) NULL,
    league_id    BIGINT       NULL,
    score_id     BIGINT       NULL,
    CONSTRAINT pk_match PRIMARY KEY (id)
);

CREATE TABLE soccer_score
(
    id                     BIGINT NOT NULL,
    final_away_score       INT    NOT NULL,
    final_home_score       INT    NOT NULL,
    first_half_away_score  INT    NOT NULL,
    first_half_home_score  INT    NOT NULL,
    second_half_away_score INT    NOT NULL,
    second_half_home_score INT    NOT NULL,
    overtime_away_score    INT    NOT NULL,
    overtime_home_score    INT    NOT NULL,
    penalty_away_score     INT    NOT NULL,
    penalty_home_score     INT    NOT NULL,
    CONSTRAINT pk_soccerscore PRIMARY KEY (id)
);

CREATE TABLE team
(
    id          BIGINT       NOT NULL,
    sport       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    external_id INT          NOT NULL,
    flag        BLOB         NULL,
    country     VARCHAR(255) NULL,
    logo        BLOB         NULL,
    CONSTRAINT pk_team PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    surname  VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_favourite_matches
(
    user_id              BIGINT NOT NULL,
    favourite_matches_id BIGINT NOT NULL
);

CREATE TABLE user_favourite_teams
(
    user_id            BIGINT NOT NULL,
    favourite_teams_id BIGINT NOT NULL
);

CREATE TABLE voleyball_score
(
    id                    BIGINT NOT NULL,
    final_away_score      INT    NOT NULL,
    final_home_score      INT    NOT NULL,
    first_set_away_score  INT    NOT NULL,
    first_set_home_score  INT    NOT NULL,
    second_set_away_score INT    NOT NULL,
    second_set_home_score INT    NOT NULL,
    third_set_away_score  INT    NOT NULL,
    third_set_home_score  INT    NOT NULL,
    fourth_set_away_score INT    NOT NULL,
    fourth_set_home_score INT    NOT NULL,
    fifth_set_away_score  INT    NOT NULL,
    fifth_set_home_score  INT    NOT NULL,
    CONSTRAINT pk_voleyballscore PRIMARY KEY (id)
);

ALTER TABLE league
    ADD CONSTRAINT uc_league_name UNIQUE (name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_AWAYTEAM FOREIGN KEY (away_team_id) REFERENCES team (id);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_HOMETEAM FOREIGN KEY (home_team_id) REFERENCES team (id);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_LEAGUE FOREIGN KEY (league_id) REFERENCES league (id);

ALTER TABLE user_favourite_matches
    ADD CONSTRAINT fk_usefavmat_on_match FOREIGN KEY (favourite_matches_id) REFERENCES `match` (id);

ALTER TABLE user_favourite_matches
    ADD CONSTRAINT fk_usefavmat_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_favourite_teams
    ADD CONSTRAINT fk_usefavtea_on_team FOREIGN KEY (favourite_teams_id) REFERENCES team (id);

ALTER TABLE user_favourite_teams
    ADD CONSTRAINT fk_usefavtea_on_user FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE basketball_score
(
    id                        BIGINT NOT NULL,
    final_away_score          INT    NOT NULL,
    final_home_score          INT    NOT NULL,
    first_quarter_away_score  INT    NOT NULL,
    first_quarter_home_score  INT    NOT NULL,
    second_quarter_away_score INT    NOT NULL,
    second_quarter_home_score INT    NOT NULL,
    third_quarter_away_score  INT    NOT NULL,
    third_quarter_home_score  INT    NOT NULL,
    fourth_quarter_away_score INT    NOT NULL,
    fourth_quarter_home_score INT    NOT NULL,
    overtime_away_score       INT    NOT NULL,
    overtime_home_score       INT    NOT NULL,
    CONSTRAINT pk_basketballscore PRIMARY KEY (id)
);

CREATE TABLE fixture
(
    id           BIGINT       NOT NULL,
    sport        VARCHAR(255) NULL,
    home_team_id BIGINT       NULL,
    away_team_id BIGINT       NULL,
    date         datetime     NULL,
    vlajka       BLOB         NULL,
    country      VARCHAR(255) NULL,
    league_id    BIGINT       NULL,
    score_id     BIGINT       NULL,
    CONSTRAINT pk_fixture PRIMARY KEY (id)
);

CREATE TABLE hockey_score
(
    id                       BIGINT NOT NULL,
    final_away_score         INT    NOT NULL,
    final_home_score         INT    NOT NULL,
    first_period_away_score  INT    NOT NULL,
    first_period_home_score  INT    NOT NULL,
    second_period_away_score INT    NOT NULL,
    second_period_home_score INT    NOT NULL,
    third_period_away_score  INT    NOT NULL,
    third_period_home_score  INT    NOT NULL,
    overtime_away_score      INT    NOT NULL,
    overtime_home_score      INT    NOT NULL,
    shootout_away_score      INT    NOT NULL,
    shootout_home_score      INT    NOT NULL,
    CONSTRAINT pk_hockeyscore PRIMARY KEY (id)
);

CREATE TABLE league
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NULL,
    type        VARCHAR(255) NULL,
    season      VARCHAR(255) NULL,
    external_id INT          NOT NULL,
    CONSTRAINT pk_league PRIMARY KEY (id)
);

CREATE TABLE soccer_score
(
    id                     BIGINT NOT NULL,
    final_away_score       INT    NOT NULL,
    final_home_score       INT    NOT NULL,
    first_half_away_score  INT    NOT NULL,
    first_half_home_score  INT    NOT NULL,
    second_half_away_score INT    NOT NULL,
    second_half_home_score INT    NOT NULL,
    overtime_away_score    INT    NOT NULL,
    overtime_home_score    INT    NOT NULL,
    penalty_away_score     INT    NOT NULL,
    penalty_home_score     INT    NOT NULL,
    CONSTRAINT pk_soccerscore PRIMARY KEY (id)
);

CREATE TABLE team
(
    id          BIGINT       NOT NULL,
    sport       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    external_id INT          NOT NULL,
    flag        BLOB         NULL,
    country     VARCHAR(255) NULL,
    logo        BLOB         NULL,
    CONSTRAINT pk_team PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    surname  VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_favourite_fixtures
(
    user_id               BIGINT NOT NULL,
    favourite_fixtures_id BIGINT NOT NULL
);

CREATE TABLE user_favourite_teams
(
    user_id            BIGINT NOT NULL,
    favourite_teams_id BIGINT NOT NULL
);

CREATE TABLE voleyball_score
(
    id                    BIGINT NOT NULL,
    final_away_score      INT    NOT NULL,
    final_home_score      INT    NOT NULL,
    first_set_away_score  INT    NOT NULL,
    first_set_home_score  INT    NOT NULL,
    second_set_away_score INT    NOT NULL,
    second_set_home_score INT    NOT NULL,
    third_set_away_score  INT    NOT NULL,
    third_set_home_score  INT    NOT NULL,
    fourth_set_away_score INT    NOT NULL,
    fourth_set_home_score INT    NOT NULL,
    fifth_set_away_score  INT    NOT NULL,
    fifth_set_home_score  INT    NOT NULL,
    CONSTRAINT pk_voleyballscore PRIMARY KEY (id)
);

ALTER TABLE league
    ADD CONSTRAINT uc_league_name UNIQUE (name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE fixture
    ADD CONSTRAINT FK_FIXTURE_ON_AWAYTEAM FOREIGN KEY (away_team_id) REFERENCES team (id);

ALTER TABLE fixture
    ADD CONSTRAINT FK_FIXTURE_ON_HOMETEAM FOREIGN KEY (home_team_id) REFERENCES team (id);

ALTER TABLE fixture
    ADD CONSTRAINT FK_FIXTURE_ON_LEAGUE FOREIGN KEY (league_id) REFERENCES league (id);

ALTER TABLE user_favourite_fixtures
    ADD CONSTRAINT fk_usefavfix_on_fixture FOREIGN KEY (favourite_fixtures_id) REFERENCES fixture (id);

ALTER TABLE user_favourite_fixtures
    ADD CONSTRAINT fk_usefavfix_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_favourite_teams
    ADD CONSTRAINT fk_usefavtea_on_team FOREIGN KEY (favourite_teams_id) REFERENCES team (id);

ALTER TABLE user_favourite_teams
    ADD CONSTRAINT fk_usefavtea_on_user FOREIGN KEY (user_id) REFERENCES user (id);