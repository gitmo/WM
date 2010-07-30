
    alter table Actor_Permission 
        drop constraint FKCF243699D36F2A65;

    alter table Actor_Permission 
        drop constraint FKCF243699401023A7;

    alter table Actor_Role 
        drop constraint FK26E2D0C0D36F2A65;

    alter table Actor_Role 
        drop constraint FK26E2D0C0E4A0B3E5;

    alter table Advisor 
        drop constraint FK1FC9F7A09B9D8D6D;

    alter table GroupStage_TournamentGroup 
        drop constraint FK62CD5967D361FB7;

    alter table GroupStage_TournamentGroup 
        drop constraint FK62CD596526DAA28;

    alter table Match 
        drop constraint FK46AE9A5D4EAEBD3;

    alter table Match 
        drop constraint FK46AE9A5F0EC562F;

    alter table Match 
        drop constraint FK46AE9A5E2487FF;

    alter table Match 
        drop constraint FK46AE9A574B12939;

    alter table Match 
        drop constraint FK46AE9A515C9D2B6;

    alter table MatchEvent 
        drop constraint FKE491D7F5C7168977;

    alter table MatchEvent 
        drop constraint FKE491D7F56A9E6294;

    alter table MatchEvent 
        drop constraint FKE491D7F560766FD;

    alter table MatchEvent 
        drop constraint FKE491D7F5DB6578D7;

    alter table MatchEvent 
        drop constraint FKE491D7F5DF1DD7B0;

    alter table Match_Match 
        drop constraint FKD7C3A68B884BBD1;

    alter table Match_Match 
        drop constraint FKD7C3A68B1FD58223;

    alter table Match_MatchEvent 
        drop constraint FKBFC93ACFDCC26853;

    alter table Match_MatchEvent 
        drop constraint FKBFC93ACF60766FD;

    alter table Permission 
        drop constraint FK57F7A1EF94470E9C;

    alter table Person_Team 
        drop constraint FK49FD4907CE781A97;

    alter table Person_Team 
        drop constraint FK49FD4907CDC8C6DE;

    alter table Player 
        drop constraint FK8EA387019B9D8D6D;

    alter table Role 
        drop constraint FK26F49674B12939;

    alter table Role 
        drop constraint FK26F496DA563832;

    alter table Role_Permission 
        drop constraint FKF8A569386AC4EDCC;

    alter table Role_Permission 
        drop constraint FKF8A56938401023A7;

    alter table Stadium 
        drop constraint FKF21D53DDFCF4FC9D;

    alter table Team 
        drop constraint FK27B67DFCF4FC9D;

    alter table Team_Advisor 
        drop constraint FKA1D587DEEA5528A;

    alter table Team_Advisor 
        drop constraint FKA1D587DEDB6578D7;

    alter table Team_Player 
        drop constraint FKD4E5F70318F27AA6;

    alter table Team_Player 
        drop constraint FKD4E5F703DB6578D7;

    alter table Tournament 
        drop constraint FK3B743609FCD043A4;

    alter table Tournament 
        drop constraint FK3B74360966DE6D99;

    alter table Tournament 
        drop constraint FK3B7436097D361FB7;

    alter table TournamentGroup 
        drop constraint FK6372DF674B12939;

    alter table TournamentGroup_Match 
        drop constraint FK3525AA1CD57DB48A;

    alter table TournamentGroup_Match 
        drop constraint FK3525AA1C2801C0AA;

    alter table TournamentGroup_Team 
        drop constraint FKB7678B26D57DB48A;

    alter table TournamentGroup_Team 
        drop constraint FKB7678B26CDC8C6DE;

    alter table Tournament_Country 
        drop constraint FK5625B34074B12939;

    alter table Tournament_Country 
        drop constraint FK5625B3409A553667;

    alter table Tournament_Stadium 
        drop constraint FKABF317A7DB034F8F;

    alter table Tournament_Stadium 
        drop constraint FKABF317A774B12939;

    drop table Actor;

    drop table Actor_Permission;

    drop table Actor_Role;

    drop table Advisor;

    drop table Country;

    drop table GroupStage;

    drop table GroupStage_TournamentGroup;

    drop table Match;

    drop table MatchEvent;

    drop table Match_Match;

    drop table Match_MatchEvent;

    drop table Permission;

    drop table Person;

    drop table Person_Team;

    drop table Player;

    drop table Resource;

    drop table Role;

    drop table Role_Permission;

    drop table Stadium;

    drop table Team;

    drop table Team_Advisor;

    drop table Team_Player;

    drop table Tournament;

    drop table TournamentGroup;

    drop table TournamentGroup_Match;

    drop table TournamentGroup_Team;

    drop table Tournament_Country;

    drop table Tournament_Stadium;

    drop sequence hibernate_sequence;

    create table Actor (
        email varchar(255) not null,
        password_hash varchar(255),
        primary key (email)
    );

    create table Actor_Permission (
        Actor_email varchar(255) not null,
        permissions_id int8 not null,
        unique (permissions_id)
    );

    create table Actor_Role (
        Actor_email varchar(255) not null,
        roles_name varchar(255) not null,
        unique (roles_name)
    );

    create table Advisor (
        task varchar(255),
        id int8 not null,
        primary key (id)
    );

    create table Country (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table GroupStage (
        id int8 not null,
        primary key (id)
    );

    create table GroupStage_TournamentGroup (
        GroupStage_id int8 not null,
        groups_groupId int8 not null,
        unique (groups_groupId)
    );

    create table Match (
        DTYPE varchar(31) not null,
        id int8 not null,
        date timestamp,
        name varchar(255),
        played bool not null,
        guestTeam_id int8,
        hostTeam_id int8,
        stadium_stadiumId int8,
        tournament_year int4,
        group_groupId int8,
        primary key (id)
    );

    create table MatchEvent (
        DTYPE varchar(31) not null,
        id int8 not null,
        additionalMinute int4 not null,
        minute int4 not null,
        color varchar(255),
        match_id int8,
        involvedPlayer_id int8,
        team_id int8,
        scorringTeam_id int8,
        newPlayer_id int8,
        primary key (id)
    );

    create table Match_Match (
        Match_id int8 not null,
        childs_id int8 not null
    );

    create table Match_MatchEvent (
        Match_id int8 not null,
        events_id int8 not null,
        unique (events_id)
    );

    create table Permission (
        id int8 not null,
        typeOfAccess int4,
        resource_id int8,
        primary key (id)
    );

    create table Person (
        id int8 not null,
        birthday timestamp,
        firstname varchar(255),
        height int4,
        lastname varchar(255),
        weight int4,
        primary key (id)
    );

    create table Person_Team (
        Person_id int8 not null,
        teams_id int8 not null
    );

    create table Player (
        club varchar(255),
        nickname varchar(255),
        id int8 not null,
        primary key (id)
    );

    create table Resource (
        id int8 not null,
        key bytea,
        name varchar(255),
        primary key (id)
    );

    create table Role (
        name varchar(255) not null,
        inheritedRole_name varchar(255),
        tournament_year int4,
        primary key (name)
    );

    create table Role_Permission (
        Role_name varchar(255) not null,
        permissions_id int8 not null,
        unique (permissions_id)
    );

    create table Stadium (
        stadiumId int8 not null,
        capacity int4 not null,
        city varchar(255),
        name varchar(255),
        country_id int8,
        primary key (stadiumId)
    );

    create table Team (
        id int8 not null,
        name varchar(255),
        country_id int8,
        primary key (id)
    );

    create table Team_Advisor (
        Team_id int8 not null,
        advisors_id int8 not null,
        unique (advisors_id)
    );

    create table Team_Player (
        Team_id int8 not null,
        players_id int8 not null
    );

    create table Tournament (
        year int4 not null,
        name varchar(255),
        finalMatch_id int8,
        groupStage_id int8,
        matchForThirdPlace_id int8,
        primary key (year)
    );

    create table TournamentGroup (
        groupId int8 not null,
        name varchar(255),
        tournament_year int4,
        primary key (groupId)
    );

    create table TournamentGroup_Match (
        TournamentGroup_groupId int8 not null,
        matches_id int8 not null,
        unique (matches_id)
    );

    create table TournamentGroup_Team (
        TournamentGroup_groupId int8 not null,
        teams_id int8 not null,
        unique (teams_id)
    );

    create table Tournament_Country (
        Tournament_year int4 not null,
        hostCountries_id int8 not null
    );

    create table Tournament_Stadium (
        Tournament_year int4 not null,
        stadiums_stadiumId int8 not null
    );

    alter table Actor_Permission 
        add constraint FKCF243699D36F2A65 
        foreign key (Actor_email) 
        references Actor;

    alter table Actor_Permission 
        add constraint FKCF243699401023A7 
        foreign key (permissions_id) 
        references Permission;

    alter table Actor_Role 
        add constraint FK26E2D0C0D36F2A65 
        foreign key (Actor_email) 
        references Actor;

    alter table Actor_Role 
        add constraint FK26E2D0C0E4A0B3E5 
        foreign key (roles_name) 
        references Role;

    alter table Advisor 
        add constraint FK1FC9F7A09B9D8D6D 
        foreign key (id) 
        references Person;

    alter table GroupStage_TournamentGroup 
        add constraint FK62CD5967D361FB7 
        foreign key (GroupStage_id) 
        references GroupStage;

    alter table GroupStage_TournamentGroup 
        add constraint FK62CD596526DAA28 
        foreign key (groups_groupId) 
        references TournamentGroup;

    alter table Match 
        add constraint FK46AE9A5D4EAEBD3 
        foreign key (group_groupId) 
        references TournamentGroup;

    alter table Match 
        add constraint FK46AE9A5F0EC562F 
        foreign key (hostTeam_id) 
        references Team;

    alter table Match 
        add constraint FK46AE9A5E2487FF 
        foreign key (guestTeam_id) 
        references Team;

    alter table Match 
        add constraint FK46AE9A574B12939 
        foreign key (tournament_year) 
        references Tournament;

    alter table Match 
        add constraint FK46AE9A515C9D2B6 
        foreign key (stadium_stadiumId) 
        references Stadium;

    alter table MatchEvent 
        add constraint FKE491D7F5C7168977 
        foreign key (newPlayer_id) 
        references Player;

    alter table MatchEvent 
        add constraint FKE491D7F56A9E6294 
        foreign key (scorringTeam_id) 
        references Team;

    alter table MatchEvent 
        add constraint FKE491D7F560766FD 
        foreign key (match_id) 
        references Match;

    alter table MatchEvent 
        add constraint FKE491D7F5DB6578D7 
        foreign key (team_id) 
        references Team;

    alter table MatchEvent 
        add constraint FKE491D7F5DF1DD7B0 
        foreign key (involvedPlayer_id) 
        references Player;

    alter table Match_Match 
        add constraint FKD7C3A68B884BBD1 
        foreign key (childs_id) 
        references Match;

    alter table Match_Match 
        add constraint FKD7C3A68B1FD58223 
        foreign key (Match_id) 
        references Match;

    alter table Match_MatchEvent 
        add constraint FKBFC93ACFDCC26853 
        foreign key (events_id) 
        references MatchEvent;

    alter table Match_MatchEvent 
        add constraint FKBFC93ACF60766FD 
        foreign key (Match_id) 
        references Match;

    alter table Permission 
        add constraint FK57F7A1EF94470E9C 
        foreign key (resource_id) 
        references Resource;

    alter table Person_Team 
        add constraint FK49FD4907CE781A97 
        foreign key (Person_id) 
        references Person;

    alter table Person_Team 
        add constraint FK49FD4907CDC8C6DE 
        foreign key (teams_id) 
        references Team;

    alter table Player 
        add constraint FK8EA387019B9D8D6D 
        foreign key (id) 
        references Person;

    alter table Role 
        add constraint FK26F49674B12939 
        foreign key (tournament_year) 
        references Tournament;

    alter table Role 
        add constraint FK26F496DA563832 
        foreign key (inheritedRole_name) 
        references Role;

    alter table Role_Permission 
        add constraint FKF8A569386AC4EDCC 
        foreign key (Role_name) 
        references Role;

    alter table Role_Permission 
        add constraint FKF8A56938401023A7 
        foreign key (permissions_id) 
        references Permission;

    alter table Stadium 
        add constraint FKF21D53DDFCF4FC9D 
        foreign key (country_id) 
        references Country;

    alter table Team 
        add constraint FK27B67DFCF4FC9D 
        foreign key (country_id) 
        references Country;

    alter table Team_Advisor 
        add constraint FKA1D587DEEA5528A 
        foreign key (advisors_id) 
        references Advisor;

    alter table Team_Advisor 
        add constraint FKA1D587DEDB6578D7 
        foreign key (Team_id) 
        references Team;

    alter table Team_Player 
        add constraint FKD4E5F70318F27AA6 
        foreign key (players_id) 
        references Player;

    alter table Team_Player 
        add constraint FKD4E5F703DB6578D7 
        foreign key (Team_id) 
        references Team;

    alter table Tournament 
        add constraint FK3B743609FCD043A4 
        foreign key (matchForThirdPlace_id) 
        references Match;

    alter table Tournament 
        add constraint FK3B74360966DE6D99 
        foreign key (finalMatch_id) 
        references Match;

    alter table Tournament 
        add constraint FK3B7436097D361FB7 
        foreign key (groupStage_id) 
        references GroupStage;

    alter table TournamentGroup 
        add constraint FK6372DF674B12939 
        foreign key (tournament_year) 
        references Tournament;

    alter table TournamentGroup_Match 
        add constraint FK3525AA1CD57DB48A 
        foreign key (TournamentGroup_groupId) 
        references TournamentGroup;

    alter table TournamentGroup_Match 
        add constraint FK3525AA1C2801C0AA 
        foreign key (matches_id) 
        references Match;

    alter table TournamentGroup_Team 
        add constraint FKB7678B26D57DB48A 
        foreign key (TournamentGroup_groupId) 
        references TournamentGroup;

    alter table TournamentGroup_Team 
        add constraint FKB7678B26CDC8C6DE 
        foreign key (teams_id) 
        references Team;

    alter table Tournament_Country 
        add constraint FK5625B34074B12939 
        foreign key (Tournament_year) 
        references Tournament;

    alter table Tournament_Country 
        add constraint FK5625B3409A553667 
        foreign key (hostCountries_id) 
        references Country;

    alter table Tournament_Stadium 
        add constraint FKABF317A7DB034F8F 
        foreign key (stadiums_stadiumId) 
        references Stadium;

    alter table Tournament_Stadium 
        add constraint FKABF317A774B12939 
        foreign key (Tournament_year) 
        references Tournament;

    create sequence hibernate_sequence;
