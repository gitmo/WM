--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = dbs_41, pg_catalog;

ALTER TABLE ONLY dbs_41.role_permission DROP CONSTRAINT fkf8a569386ac4edcc;
ALTER TABLE ONLY dbs_41.role_permission DROP CONSTRAINT fkf8a56938401023a7;
ALTER TABLE ONLY dbs_41.stadium DROP CONSTRAINT fkf21d53ddfcf4fc9d;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT fke491d7f5df1dd7b0;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT fke491d7f5db6578d7;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT fke491d7f5c7168977;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT fke491d7f56a9e6294;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT fke491d7f560766fd;
ALTER TABLE ONLY dbs_41.match_match DROP CONSTRAINT fkd7c3a68b884bbd1;
ALTER TABLE ONLY dbs_41.match_match DROP CONSTRAINT fkd7c3a68b1fd58223;
ALTER TABLE ONLY dbs_41.team_player DROP CONSTRAINT fkd4e5f703db6578d7;
ALTER TABLE ONLY dbs_41.team_player DROP CONSTRAINT fkd4e5f70318f27aa6;
ALTER TABLE ONLY dbs_41.actor_permission DROP CONSTRAINT fkcf243699d36f2a65;
ALTER TABLE ONLY dbs_41.actor_permission DROP CONSTRAINT fkcf243699401023a7;
ALTER TABLE ONLY dbs_41.match_matchevent DROP CONSTRAINT fkbfc93acfdcc26853;
ALTER TABLE ONLY dbs_41.match_matchevent DROP CONSTRAINT fkbfc93acf60766fd;
ALTER TABLE ONLY dbs_41.tournamentgroup_team DROP CONSTRAINT fkb7678b26d57db48a;
ALTER TABLE ONLY dbs_41.tournamentgroup_team DROP CONSTRAINT fkb7678b26cdc8c6de;
ALTER TABLE ONLY dbs_41.tournament_stadium DROP CONSTRAINT fkabf317a7db034f8f;
ALTER TABLE ONLY dbs_41.tournament_stadium DROP CONSTRAINT fkabf317a774b12939;
ALTER TABLE ONLY dbs_41.team_advisor DROP CONSTRAINT fka1d587deea5528a;
ALTER TABLE ONLY dbs_41.team_advisor DROP CONSTRAINT fka1d587dedb6578d7;
ALTER TABLE ONLY dbs_41.player DROP CONSTRAINT fk8ea387019b9d8d6d;
ALTER TABLE ONLY dbs_41.tournamentgroup DROP CONSTRAINT fk6372df674b12939;
ALTER TABLE ONLY dbs_41.groupstage_tournamentgroup DROP CONSTRAINT fk62cd5967d361fb7;
ALTER TABLE ONLY dbs_41.groupstage_tournamentgroup DROP CONSTRAINT fk62cd596526daa28;
ALTER TABLE ONLY dbs_41.permission DROP CONSTRAINT fk57f7a1ef94470e9c;
ALTER TABLE ONLY dbs_41.tournament_country DROP CONSTRAINT fk5625b3409a553667;
ALTER TABLE ONLY dbs_41.tournament_country DROP CONSTRAINT fk5625b34074b12939;
ALTER TABLE ONLY dbs_41.person_team DROP CONSTRAINT fk49fd4907ce781a97;
ALTER TABLE ONLY dbs_41.person_team DROP CONSTRAINT fk49fd4907cdc8c6de;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT fk46ae9a5f0ec562f;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT fk46ae9a5e2487ff;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT fk46ae9a5d4eaebd3;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT fk46ae9a574b12939;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT fk46ae9a515c9d2b6;
ALTER TABLE ONLY dbs_41.tournament DROP CONSTRAINT fk3b743609fcd043a4;
ALTER TABLE ONLY dbs_41.tournament DROP CONSTRAINT fk3b7436097d361fb7;
ALTER TABLE ONLY dbs_41.tournament DROP CONSTRAINT fk3b74360966de6d99;
ALTER TABLE ONLY dbs_41.tournamentgroup_match DROP CONSTRAINT fk3525aa1cd57db48a;
ALTER TABLE ONLY dbs_41.tournamentgroup_match DROP CONSTRAINT fk3525aa1c2801c0aa;
ALTER TABLE ONLY dbs_41.team DROP CONSTRAINT fk27b67dfcf4fc9d;
ALTER TABLE ONLY dbs_41.role DROP CONSTRAINT fk26f496da563832;
ALTER TABLE ONLY dbs_41.role DROP CONSTRAINT fk26f49674b12939;
ALTER TABLE ONLY dbs_41.actor_role DROP CONSTRAINT fk26e2d0c0e4a0b3e5;
ALTER TABLE ONLY dbs_41.actor_role DROP CONSTRAINT fk26e2d0c0d36f2a65;
ALTER TABLE ONLY dbs_41.advisor DROP CONSTRAINT fk1fc9f7a09b9d8d6d;
ALTER TABLE ONLY dbs_41.tournamentgroup_team DROP CONSTRAINT tournamentgroup_team_teams_id_key;
ALTER TABLE ONLY dbs_41.tournamentgroup DROP CONSTRAINT tournamentgroup_pkey;
ALTER TABLE ONLY dbs_41.tournamentgroup_match DROP CONSTRAINT tournamentgroup_match_matches_id_key;
ALTER TABLE ONLY dbs_41.tournament DROP CONSTRAINT tournament_pkey;
ALTER TABLE ONLY dbs_41.team DROP CONSTRAINT team_pkey;
ALTER TABLE ONLY dbs_41.team_advisor DROP CONSTRAINT team_advisor_advisors_id_key;
ALTER TABLE ONLY dbs_41.stadium DROP CONSTRAINT stadium_pkey;
ALTER TABLE ONLY dbs_41.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY dbs_41.role_permission DROP CONSTRAINT role_permission_permissions_id_key;
ALTER TABLE ONLY dbs_41.resource DROP CONSTRAINT resource_pkey;
ALTER TABLE ONLY dbs_41.player DROP CONSTRAINT player_pkey;
ALTER TABLE ONLY dbs_41.person DROP CONSTRAINT person_pkey;
ALTER TABLE ONLY dbs_41.permission DROP CONSTRAINT permission_pkey;
ALTER TABLE ONLY dbs_41.matchevent DROP CONSTRAINT matchevent_pkey;
ALTER TABLE ONLY dbs_41.match DROP CONSTRAINT match_pkey;
ALTER TABLE ONLY dbs_41.match_matchevent DROP CONSTRAINT match_matchevent_events_id_key;
ALTER TABLE ONLY dbs_41.groupstage_tournamentgroup DROP CONSTRAINT groupstage_tournamentgroup_groups_groupid_key;
ALTER TABLE ONLY dbs_41.groupstage DROP CONSTRAINT groupstage_pkey;
ALTER TABLE ONLY dbs_41.country DROP CONSTRAINT country_pkey;
ALTER TABLE ONLY dbs_41.advisor DROP CONSTRAINT advisor_pkey;
ALTER TABLE ONLY dbs_41.actor_role DROP CONSTRAINT actor_role_roles_name_key;
ALTER TABLE ONLY dbs_41.actor DROP CONSTRAINT actor_pkey;
ALTER TABLE ONLY dbs_41.actor_permission DROP CONSTRAINT actor_permission_permissions_id_key;
DROP TABLE dbs_41.tournamentgroup_team;
DROP TABLE dbs_41.tournamentgroup_match;
DROP TABLE dbs_41.tournamentgroup;
DROP TABLE dbs_41.tournament_stadium;
DROP TABLE dbs_41.tournament_country;
DROP TABLE dbs_41.tournament;
DROP TABLE dbs_41.team_player;
DROP TABLE dbs_41.team_advisor;
DROP TABLE dbs_41.role_permission;
DROP TABLE dbs_41.role;
DROP TABLE dbs_41.resource;
DROP TABLE dbs_41.person_team;
DROP TABLE dbs_41.person;
DROP TABLE dbs_41.permission;
DROP TABLE dbs_41.matchevent;
DROP TABLE dbs_41.match_matchevent;
DROP TABLE dbs_41.match_match;
DROP TABLE dbs_41.match;
DROP SEQUENCE dbs_41.hibernate_sequence;
DROP TABLE dbs_41.groupstage_tournamentgroup;
DROP TABLE dbs_41.advisor;
DROP TABLE dbs_41.actor_role;
DROP TABLE dbs_41.actor_permission;
DROP TABLE dbs_41.actor;
DROP FUNCTION dbs_41.getstadiumsforcountry(bigint);
DROP TABLE dbs_41.stadium;
DROP FUNCTION dbs_41.getplayer();
DROP TABLE dbs_41.player;
DROP FUNCTION dbs_41.getnextsequence();
DROP FUNCTION dbs_41.getcountry();
DROP TABLE dbs_41.country;
DROP FUNCTION dbs_41.generateteam();
DROP TABLE dbs_41.team;
DROP FUNCTION dbs_41.generatematch(bigint, bigint, bigint);
DROP FUNCTION dbs_41.generateknockouttree(integer, bigint);
DROP FUNCTION dbs_41.generategroupstage();
DROP TABLE dbs_41.groupstage;
DROP FUNCTION dbs_41.generategroupmatches(bigint);
DROP FUNCTION dbs_41.createchampionship(integer, text);
DROP FUNCTION dbs_41.concat(character varying, bigint);
DROP FUNCTION dbs_41.concat(character varying, integer);
DROP PROCEDURAL LANGUAGE plpgsql;
--DROP SCHEMA dbs_41;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

--CREATE SCHEMA dbs_41;


ALTER SCHEMA dbs_41 OWNER TO dbs_41;

--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO dbs_41;

SET search_path = dbs_41, pg_catalog;

--
-- Name: concat(character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION concat(character varying, integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
BEGIN
	return $1 || ' ' || chr(49 + ($2%119));
END
$_$;


ALTER FUNCTION dbs_41.concat(character varying, integer) OWNER TO dbs_41;

--
-- Name: concat(character varying, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION concat(character varying, bigint) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
BEGIN
	return $1 || ' ' || chr(CAST(49 + ($2%119) AS INT));
END
$_$;


ALTER FUNCTION dbs_41.concat(character varying, bigint) OWNER TO dbs_41;

--
-- Name: createchampionship(integer, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION createchampionship(integer, text) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
	yearParam ALIAS FOR $1;
	nameParam ALIAS FOR $2;
	host country%ROWTYPE;
	currentStadium stadium%ROWTYPE;
	groupStage groupstage%ROWTYPE;
	finalId bigint;
BEGIN
	RAISE NOTICE 'Creating a new tournament';

  --Generiert die K.O.-Phase
	finalId := getNextSequence();
	INSERT INTO match(id, name, played, dtype)
	VALUES (finalId, 'Finale', false, 'KnockoutMatch');
	PERFORM generateKnockoutTree(1, finalId);
	
	--Generiert die Gruppenphase
	groupStage := generateGroupStage();
	
	--Speichert das Turnier ab
	INSERT INTO tournament (year, name, finalmatch_id, groupstage_id)
	VALUES (yearParam, nameParam, finalId, groupStage.id);
  
	-- Set a random host country
	host := getCountry();
	INSERT INTO "tournament_country" VALUES (yearParam, host.id);

	-- Set 8 random stadiums
	FOR currentStadium IN SELECT * FROM getStadiumsForCountry(host.id) LOOP
		INSERT INTO tournament_stadium VALUES(yearParam,currentStadium.stadiumid);
	END LOOP;  
  RETURN;
END;
$_$;


ALTER FUNCTION dbs_41.createchampionship(integer, text) OWNER TO dbs_41;

--
-- Name: generategroupmatches(bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION generategroupmatches(bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
	groupId ALIAS FOR $1;
	numberOfTeams int;
	currentTeam team%ROWTYPE;
	teams team[];
	i int;
	j int;
BEGIN

	SELECT COUNT(*) INTO numberOfTeams
	FROM tournamentgroup_team
	WHERE tournamentgroup_groupid = groupId;

  -- Test ob genuegend Teams in der Gruppe sind
	if(numberOfTeams < 4)  THEN
		RAISE EXCEPTION 'at least 4 teams have to be in a group';
		RETURN;
	END IF;

	
	-- Erstellt ein Array aus dem Teams der Gruppe
	teams := '{}';
	FOR currentTeam IN 
		SELECT t.*
		FROM team t
		JOIN tournamentgroup_team g ON (g.teams_id = t.id)
		WHERE tournamentgroup_groupid = groupId
	LOOP
		teams := array_append(teams, currentTeam);
	END LOOP;

  -- Laesst jede Mannschaft einmal gegen alle anderen Manschaften antreten
	FOR i IN 1..4 LOOP
		FOR j IN (i+1)..4 LOOP
			PERFORM generateMatch(teams[i].id, teams[j].id, groupId);
		END LOOP;
	END LOOP;

	return;

END
$_$;


ALTER FUNCTION dbs_41.generategroupmatches(bigint) OWNER TO dbs_41;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: groupstage; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groupstage (
    id bigint NOT NULL
);


ALTER TABLE dbs_41.groupstage OWNER TO dbs_41;

--
-- Name: generategroupstage(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION generategroupstage() RETURNS groupstage
    LANGUAGE plpgsql
    AS $$
DECLARE
	stageId int;
	stage groupstage;
	currentTeam team;
	currentGroup tournamentgroup;
	currentGroupId bigint;
	i int;
	j int;
BEGIN
	stageId := getNextSequence();

	INSERT INTO groupstage VALUES (stageId);
	
	-- Fuer alle 8 Gruppen
	FOR i IN 1..8 LOOP
		currentGroupId := getNextSequence();
		
		INSERT INTO tournamentgroup (groupid, name)
		VALUES (currentGroupId, concat('Gruppe', 10));
		
		-- generiere 4 Mannschaften
		FOR j IN 1..4 LOOP
			currentTeam := generateTeam();
			
			INSERT INTO tournamentgroup_team (tournamentgroup_groupid, teams_id)
			VALUES (currentGroupId, currentTeam.id);
		END LOOP;

		INSERT INTO groupstage_tournamentgroup VALUES (stageId, currentGroupId);

    -- und trage die Gruppenspiele ein
		PERFORM generateGroupMatches(currentGroupId);
		
	END LOOP;

	SELECT * INTO stage FROM groupstage WHERE id = stageId;

	return stage;
	
END
$$;


ALTER FUNCTION dbs_41.generategroupstage() OWNER TO dbs_41;

--
-- Name: generateknockouttree(integer, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION generateknockouttree(integer, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
	height ALIAS FOR $1;
	nodeId ALIAS FOR $2;
	matchId1 bigint;
	matchId2 bigint;
	newHeight int;
	knockoutMatchType varchar;
BEGIN
  --Rekursionsanker
	IF(height > 3) THEN
		RETURN;
	ELSIF (height = 1) THEN
		knockoutMatchType := 'Halbfinale';
	ELSIF (height = 2) THEN
		knockoutMatchType := 'Viertelfinale';
	ELSIF (height = 3) THEN
		knockoutMatchType := 'Achtelfinale';
	END IF;


  -- Erstellen zweier Kindspiele
	matchId1 := getNextSequence();
	INSERT INTO match(id, name, played, dtype)
	VALUES (matchId1, knockoutMatchType, false, 'KnockoutMatch');
	
	matchId2 := getNextSequence();
	INSERT INTO match(id, name, played, dtype)
	VALUES (matchId2, knockoutMatchType, false, 'KnockoutMatch');

  -- Hinzufuegen zum Baum
	INSERT INTO match_match(match_id, childs_id) VALUES (nodeId, matchId1);
	INSERT INTO match_match(match_id, childs_id) VALUES (nodeId, matchId2);

  -- rekursiver Aufruf
	newHeight := height + 1;
	PERFORM generateKnockoutTree(newHeight, matchId1);
	PERFORM generateKnockoutTree(newHeight, matchId2);

	RETURN;
END;
$_$;


ALTER FUNCTION dbs_41.generateknockouttree(integer, bigint) OWNER TO dbs_41;

--
-- Name: generatematch(bigint, bigint, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION generatematch(bigint, bigint, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
	hostTeam ALIAS FOR $1;
	guestTeam ALIAS FOR $2;
	groupId ALIAS FOR $3;
	matchId bigint;
	i int;
BEGIN
	matchId := getNextSequence();
	
	INSERT INTO match(id, hostteam_id, guestteam_id, played, dtype,  group_groupid)
	VALUES (matchId, hostTeam, guestTeam, false,'GroupMatch', groupId);
	
	INSERT INTO tournamentgroup_match
	VALUES (groupId, matchId);
END
$_$;


ALTER FUNCTION dbs_41.generatematch(bigint, bigint, bigint) OWNER TO dbs_41;

--
-- Name: team; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE team (
    id bigint NOT NULL,
    name character varying(255),
    country_id bigint
);


ALTER TABLE dbs_41.team OWNER TO dbs_41;

--
-- Name: generateteam(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION generateteam() RETURNS team
    LANGUAGE plpgsql
    AS $$
DECLARE
	i int;
	j int;
	sequenceValue int;
	playerId int;
	selectedTeam Team%ROWTYPE;
BEGIN
	SELECT id INTO i FROM getCountry();

	sequenceValue := getNextSequence();
	
	INSERT INTO team VALUES (sequenceValue, concat('Musterteam ', sequenceValue), i);
	SELECT * INTO selectedTeam FROM team WHERE id = sequenceValue;

	FOR j IN 1..23 LOOP
		SELECT id INTO playerId FROM getPlayer();
		INSERT INTO team_player VALUES (selectedTeam.id, playerId);
		INSERT INTO person_team VALUES (playerId, selectedTeam.id);
	END LOOP;
	
	return selectedTeam;
END
$$;


ALTER FUNCTION dbs_41.generateteam() OWNER TO dbs_41;

--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE country (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE dbs_41.country OWNER TO dbs_41;

--
-- Name: getcountry(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getcountry() RETURNS country
    LANGUAGE plpgsql
    AS $$
DECLARE
	selectedRow Country%ROWTYPE;
	n int := 0;
BEGIN
	SELECT COUNT(*) INTO n FROM Country;
	IF(n < 1) THEN
		INSERT INTO Country VALUES (getNextSequence(), 'DummyLand');
	END IF;
	
	SELECT * INTO selectedRow FROM Country ORDER BY RANDOM() LIMIT 1;
	RETURN selectedRow;
END
$$;


ALTER FUNCTION dbs_41.getcountry() OWNER TO dbs_41;

--
-- Name: getnextsequence(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getnextsequence() RETURNS bigint
    LANGUAGE sql
    AS $$
	SELECT nextval('hibernate_sequence') FROM hibernate_sequence;
$$;


ALTER FUNCTION dbs_41.getnextsequence() OWNER TO dbs_41;

--
-- Name: player; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE player (
    club character varying(255),
    nickname character varying(255),
    id bigint NOT NULL
);


ALTER TABLE dbs_41.player OWNER TO dbs_41;

--
-- Name: getplayer(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getplayer() RETURNS player
    LANGUAGE plpgsql
    AS $$
DECLARE
	createdPlayer Player%ROWTYPE;
	sequenceValue bigint;
BEGIN
	sequenceValue := getNextSequence();
	
	INSERT INTO person (id, firstname, lastname) 
	VALUES (sequenceValue, concat('Vorname', sequenceValue), concat('Nachname', sequenceValue));

	INSERT INTO player (id, nickname, club)
	VALUES (sequenceValue, concat('Nick', sequenceValue), 'FC Seehaeusl');

	SELECT * INTO createdPlayer FROM player WHERE id = sequenceValue;

	return createdPlayer;
END
$$;


ALTER FUNCTION dbs_41.getplayer() OWNER TO dbs_41;

--
-- Name: stadium; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE stadium (
    stadiumid bigint NOT NULL,
    capacity integer NOT NULL,
    city character varying(255),
    name character varying(255),
    country_id bigint
);


ALTER TABLE dbs_41.stadium OWNER TO dbs_41;

--
-- Name: getstadiumsforcountry(bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getstadiumsforcountry(bigint) RETURNS SETOF stadium
    LANGUAGE plpgsql
    AS $_$
DECLARE
	countryId ALIAS FOR $1;
	selectedRow Stadium%ROWTYPE;
	n int := 0;
	i int;
BEGIN	
	SELECT COUNT(*) INTO n FROM Stadium WHERE country_id = countryId;
	IF(n < 8) THEN
		FOR i IN 1..(8-n) LOOP
			INSERT INTO Stadium VALUES (getNextSequence(), 500, concat('Dummystadt',i) , concat('Dummystadion',i), countryId);
		END LOOP;
	END IF;

	FOR selectedRow IN SELECT * FROM stadium ORDER BY RANDOM() LIMIT 8 LOOP
		return next selectedRow;
	END LOOP;
	
	return;
END
$_$;


ALTER FUNCTION dbs_41.getstadiumsforcountry(bigint) OWNER TO dbs_41;

--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actor (
    email character varying(255) NOT NULL,
    password_hash character varying(255)
);


ALTER TABLE dbs_41.actor OWNER TO dbs_41;

--
-- Name: actor_permission; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actor_permission (
    actor_email character varying(255) NOT NULL,
    permissions_id bigint NOT NULL
);


ALTER TABLE dbs_41.actor_permission OWNER TO dbs_41;

--
-- Name: actor_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actor_role (
    actor_email character varying(255) NOT NULL,
    roles_name character varying(255) NOT NULL
);


ALTER TABLE dbs_41.actor_role OWNER TO dbs_41;

--
-- Name: advisor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE advisor (
    task character varying(255),
    id bigint NOT NULL
);


ALTER TABLE dbs_41.advisor OWNER TO dbs_41;

--
-- Name: groupstage_tournamentgroup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groupstage_tournamentgroup (
    groupstage_id bigint NOT NULL,
    groups_groupid bigint NOT NULL
);


ALTER TABLE dbs_41.groupstage_tournamentgroup OWNER TO dbs_41;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE dbs_41.hibernate_sequence OWNER TO dbs_41;

--
-- Name: match; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE match (
    dtype character varying(31) NOT NULL,
    id bigint NOT NULL,
    date timestamp without time zone,
    name character varying(255),
    played boolean NOT NULL,
    guestteam_id bigint,
    hostteam_id bigint,
    stadium_stadiumid bigint,
    tournament_year integer,
    group_groupid bigint
);


ALTER TABLE dbs_41.match OWNER TO dbs_41;

--
-- Name: match_match; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE match_match (
    match_id bigint NOT NULL,
    childs_id bigint NOT NULL
);


ALTER TABLE dbs_41.match_match OWNER TO dbs_41;

--
-- Name: match_matchevent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE match_matchevent (
    match_id bigint NOT NULL,
    events_id bigint NOT NULL
);


ALTER TABLE dbs_41.match_matchevent OWNER TO dbs_41;

--
-- Name: matchevent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE matchevent (
    dtype character varying(31) NOT NULL,
    id bigint NOT NULL,
    additionalminute integer NOT NULL,
    minute integer NOT NULL,
    color character varying(255),
    match_id bigint,
    involvedplayer_id bigint,
    team_id bigint,
    scorringteam_id bigint,
    newplayer_id bigint
);


ALTER TABLE dbs_41.matchevent OWNER TO dbs_41;

--
-- Name: permission; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permission (
    id bigint NOT NULL,
    typeofaccess integer,
    resource_id bigint
);


ALTER TABLE dbs_41.permission OWNER TO dbs_41;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person (
    id bigint NOT NULL,
    birthday timestamp without time zone,
    firstname character varying(255),
    height integer,
    lastname character varying(255),
    weight integer
);


ALTER TABLE dbs_41.person OWNER TO dbs_41;

--
-- Name: person_team; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person_team (
    person_id bigint NOT NULL,
    teams_id bigint NOT NULL
);


ALTER TABLE dbs_41.person_team OWNER TO dbs_41;

--
-- Name: resource; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resource (
    id bigint NOT NULL,
    key bytea,
    name character varying(255)
);


ALTER TABLE dbs_41.resource OWNER TO dbs_41;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    name character varying(255) NOT NULL,
    inheritedrole_name character varying(255),
    tournament_year integer
);


ALTER TABLE dbs_41.role OWNER TO dbs_41;

--
-- Name: role_permission; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE role_permission (
    role_name character varying(255) NOT NULL,
    permissions_id bigint NOT NULL
);


ALTER TABLE dbs_41.role_permission OWNER TO dbs_41;

--
-- Name: team_advisor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE team_advisor (
    team_id bigint NOT NULL,
    advisors_id bigint NOT NULL
);


ALTER TABLE dbs_41.team_advisor OWNER TO dbs_41;

--
-- Name: team_player; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE team_player (
    team_id bigint NOT NULL,
    players_id bigint NOT NULL
);


ALTER TABLE dbs_41.team_player OWNER TO dbs_41;

--
-- Name: tournament; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournament (
    year integer NOT NULL,
    name character varying(255),
    finalmatch_id bigint,
    groupstage_id bigint,
    matchforthirdplace_id bigint
);


ALTER TABLE dbs_41.tournament OWNER TO dbs_41;

--
-- Name: tournament_country; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournament_country (
    tournament_year integer NOT NULL,
    hostcountries_id bigint NOT NULL
);


ALTER TABLE dbs_41.tournament_country OWNER TO dbs_41;

--
-- Name: tournament_stadium; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournament_stadium (
    tournament_year integer NOT NULL,
    stadiums_stadiumid bigint NOT NULL
);


ALTER TABLE dbs_41.tournament_stadium OWNER TO dbs_41;

--
-- Name: tournamentgroup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournamentgroup (
    groupid bigint NOT NULL,
    name character varying(255),
    tournament_year integer
);


ALTER TABLE dbs_41.tournamentgroup OWNER TO dbs_41;

--
-- Name: tournamentgroup_match; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournamentgroup_match (
    tournamentgroup_groupid bigint NOT NULL,
    matches_id bigint NOT NULL
);


ALTER TABLE dbs_41.tournamentgroup_match OWNER TO dbs_41;

--
-- Name: tournamentgroup_team; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tournamentgroup_team (
    tournamentgroup_groupid bigint NOT NULL,
    teams_id bigint NOT NULL
);


ALTER TABLE dbs_41.tournamentgroup_team OWNER TO dbs_41;

--
-- Name: actor_permission_permissions_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actor_permission
    ADD CONSTRAINT actor_permission_permissions_id_key UNIQUE (permissions_id);


--
-- Name: actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actor
    ADD CONSTRAINT actor_pkey PRIMARY KEY (email);


--
-- Name: actor_role_roles_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actor_role
    ADD CONSTRAINT actor_role_roles_name_key UNIQUE (roles_name);


--
-- Name: advisor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY advisor
    ADD CONSTRAINT advisor_pkey PRIMARY KEY (id);


--
-- Name: country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: groupstage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groupstage
    ADD CONSTRAINT groupstage_pkey PRIMARY KEY (id);


--
-- Name: groupstage_tournamentgroup_groups_groupid_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groupstage_tournamentgroup
    ADD CONSTRAINT groupstage_tournamentgroup_groups_groupid_key UNIQUE (groups_groupid);


--
-- Name: match_matchevent_events_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY match_matchevent
    ADD CONSTRAINT match_matchevent_events_id_key UNIQUE (events_id);


--
-- Name: match_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY match
    ADD CONSTRAINT match_pkey PRIMARY KEY (id);


--
-- Name: matchevent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT matchevent_pkey PRIMARY KEY (id);


--
-- Name: permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- Name: resource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resource
    ADD CONSTRAINT resource_pkey PRIMARY KEY (id);


--
-- Name: role_permission_permissions_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT role_permission_permissions_id_key UNIQUE (permissions_id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (name);


--
-- Name: stadium_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stadium
    ADD CONSTRAINT stadium_pkey PRIMARY KEY (stadiumid);


--
-- Name: team_advisor_advisors_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY team_advisor
    ADD CONSTRAINT team_advisor_advisors_id_key UNIQUE (advisors_id);


--
-- Name: team_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- Name: tournament_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tournament
    ADD CONSTRAINT tournament_pkey PRIMARY KEY (year);


--
-- Name: tournamentgroup_match_matches_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tournamentgroup_match
    ADD CONSTRAINT tournamentgroup_match_matches_id_key UNIQUE (matches_id);


--
-- Name: tournamentgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tournamentgroup
    ADD CONSTRAINT tournamentgroup_pkey PRIMARY KEY (groupid);


--
-- Name: tournamentgroup_team_teams_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tournamentgroup_team
    ADD CONSTRAINT tournamentgroup_team_teams_id_key UNIQUE (teams_id);


--
-- Name: fk1fc9f7a09b9d8d6d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY advisor
    ADD CONSTRAINT fk1fc9f7a09b9d8d6d FOREIGN KEY (id) REFERENCES person(id);


--
-- Name: fk26e2d0c0d36f2a65; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor_role
    ADD CONSTRAINT fk26e2d0c0d36f2a65 FOREIGN KEY (actor_email) REFERENCES actor(email);


--
-- Name: fk26e2d0c0e4a0b3e5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor_role
    ADD CONSTRAINT fk26e2d0c0e4a0b3e5 FOREIGN KEY (roles_name) REFERENCES role(name);


--
-- Name: fk26f49674b12939; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT fk26f49674b12939 FOREIGN KEY (tournament_year) REFERENCES tournament(year);


--
-- Name: fk26f496da563832; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT fk26f496da563832 FOREIGN KEY (inheritedrole_name) REFERENCES role(name);


--
-- Name: fk27b67dfcf4fc9d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team
    ADD CONSTRAINT fk27b67dfcf4fc9d FOREIGN KEY (country_id) REFERENCES country(id);


--
-- Name: fk3525aa1c2801c0aa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournamentgroup_match
    ADD CONSTRAINT fk3525aa1c2801c0aa FOREIGN KEY (matches_id) REFERENCES match(id);


--
-- Name: fk3525aa1cd57db48a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournamentgroup_match
    ADD CONSTRAINT fk3525aa1cd57db48a FOREIGN KEY (tournamentgroup_groupid) REFERENCES tournamentgroup(groupid);


--
-- Name: fk3b74360966de6d99; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament
    ADD CONSTRAINT fk3b74360966de6d99 FOREIGN KEY (finalmatch_id) REFERENCES match(id);


--
-- Name: fk3b7436097d361fb7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament
    ADD CONSTRAINT fk3b7436097d361fb7 FOREIGN KEY (groupstage_id) REFERENCES groupstage(id);


--
-- Name: fk3b743609fcd043a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament
    ADD CONSTRAINT fk3b743609fcd043a4 FOREIGN KEY (matchforthirdplace_id) REFERENCES match(id);


--
-- Name: fk46ae9a515c9d2b6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match
    ADD CONSTRAINT fk46ae9a515c9d2b6 FOREIGN KEY (stadium_stadiumid) REFERENCES stadium(stadiumid);


--
-- Name: fk46ae9a574b12939; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match
    ADD CONSTRAINT fk46ae9a574b12939 FOREIGN KEY (tournament_year) REFERENCES tournament(year);


--
-- Name: fk46ae9a5d4eaebd3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match
    ADD CONSTRAINT fk46ae9a5d4eaebd3 FOREIGN KEY (group_groupid) REFERENCES tournamentgroup(groupid);


--
-- Name: fk46ae9a5e2487ff; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match
    ADD CONSTRAINT fk46ae9a5e2487ff FOREIGN KEY (guestteam_id) REFERENCES team(id);


--
-- Name: fk46ae9a5f0ec562f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match
    ADD CONSTRAINT fk46ae9a5f0ec562f FOREIGN KEY (hostteam_id) REFERENCES team(id);


--
-- Name: fk49fd4907cdc8c6de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_team
    ADD CONSTRAINT fk49fd4907cdc8c6de FOREIGN KEY (teams_id) REFERENCES team(id);


--
-- Name: fk49fd4907ce781a97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_team
    ADD CONSTRAINT fk49fd4907ce781a97 FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk5625b34074b12939; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament_country
    ADD CONSTRAINT fk5625b34074b12939 FOREIGN KEY (tournament_year) REFERENCES tournament(year);


--
-- Name: fk5625b3409a553667; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament_country
    ADD CONSTRAINT fk5625b3409a553667 FOREIGN KEY (hostcountries_id) REFERENCES country(id);


--
-- Name: fk57f7a1ef94470e9c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT fk57f7a1ef94470e9c FOREIGN KEY (resource_id) REFERENCES resource(id);


--
-- Name: fk62cd596526daa28; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groupstage_tournamentgroup
    ADD CONSTRAINT fk62cd596526daa28 FOREIGN KEY (groups_groupid) REFERENCES tournamentgroup(groupid);


--
-- Name: fk62cd5967d361fb7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groupstage_tournamentgroup
    ADD CONSTRAINT fk62cd5967d361fb7 FOREIGN KEY (groupstage_id) REFERENCES groupstage(id);


--
-- Name: fk6372df674b12939; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournamentgroup
    ADD CONSTRAINT fk6372df674b12939 FOREIGN KEY (tournament_year) REFERENCES tournament(year);


--
-- Name: fk8ea387019b9d8d6d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT fk8ea387019b9d8d6d FOREIGN KEY (id) REFERENCES person(id);


--
-- Name: fka1d587dedb6578d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team_advisor
    ADD CONSTRAINT fka1d587dedb6578d7 FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: fka1d587deea5528a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team_advisor
    ADD CONSTRAINT fka1d587deea5528a FOREIGN KEY (advisors_id) REFERENCES advisor(id);


--
-- Name: fkabf317a774b12939; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament_stadium
    ADD CONSTRAINT fkabf317a774b12939 FOREIGN KEY (tournament_year) REFERENCES tournament(year);


--
-- Name: fkabf317a7db034f8f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournament_stadium
    ADD CONSTRAINT fkabf317a7db034f8f FOREIGN KEY (stadiums_stadiumid) REFERENCES stadium(stadiumid);


--
-- Name: fkb7678b26cdc8c6de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournamentgroup_team
    ADD CONSTRAINT fkb7678b26cdc8c6de FOREIGN KEY (teams_id) REFERENCES team(id);


--
-- Name: fkb7678b26d57db48a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tournamentgroup_team
    ADD CONSTRAINT fkb7678b26d57db48a FOREIGN KEY (tournamentgroup_groupid) REFERENCES tournamentgroup(groupid);


--
-- Name: fkbfc93acf60766fd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match_matchevent
    ADD CONSTRAINT fkbfc93acf60766fd FOREIGN KEY (match_id) REFERENCES match(id);


--
-- Name: fkbfc93acfdcc26853; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match_matchevent
    ADD CONSTRAINT fkbfc93acfdcc26853 FOREIGN KEY (events_id) REFERENCES matchevent(id);


--
-- Name: fkcf243699401023a7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor_permission
    ADD CONSTRAINT fkcf243699401023a7 FOREIGN KEY (permissions_id) REFERENCES permission(id);


--
-- Name: fkcf243699d36f2a65; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actor_permission
    ADD CONSTRAINT fkcf243699d36f2a65 FOREIGN KEY (actor_email) REFERENCES actor(email);


--
-- Name: fkd4e5f70318f27aa6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team_player
    ADD CONSTRAINT fkd4e5f70318f27aa6 FOREIGN KEY (players_id) REFERENCES player(id);


--
-- Name: fkd4e5f703db6578d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY team_player
    ADD CONSTRAINT fkd4e5f703db6578d7 FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: fkd7c3a68b1fd58223; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match_match
    ADD CONSTRAINT fkd7c3a68b1fd58223 FOREIGN KEY (match_id) REFERENCES match(id);


--
-- Name: fkd7c3a68b884bbd1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY match_match
    ADD CONSTRAINT fkd7c3a68b884bbd1 FOREIGN KEY (childs_id) REFERENCES match(id);


--
-- Name: fke491d7f560766fd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT fke491d7f560766fd FOREIGN KEY (match_id) REFERENCES match(id);


--
-- Name: fke491d7f56a9e6294; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT fke491d7f56a9e6294 FOREIGN KEY (scorringteam_id) REFERENCES team(id);


--
-- Name: fke491d7f5c7168977; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT fke491d7f5c7168977 FOREIGN KEY (newplayer_id) REFERENCES player(id);


--
-- Name: fke491d7f5db6578d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT fke491d7f5db6578d7 FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: fke491d7f5df1dd7b0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matchevent
    ADD CONSTRAINT fke491d7f5df1dd7b0 FOREIGN KEY (involvedplayer_id) REFERENCES player(id);


--
-- Name: fkf21d53ddfcf4fc9d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stadium
    ADD CONSTRAINT fkf21d53ddfcf4fc9d FOREIGN KEY (country_id) REFERENCES country(id);


--
-- Name: fkf8a56938401023a7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT fkf8a56938401023a7 FOREIGN KEY (permissions_id) REFERENCES permission(id);


--
-- Name: fkf8a569386ac4edcc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT fkf8a569386ac4edcc FOREIGN KEY (role_name) REFERENCES role(name);


--
-- PostgreSQL database dump complete
--

