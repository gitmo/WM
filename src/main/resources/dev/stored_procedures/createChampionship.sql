--
-- Erstellt einen Turnierplan
--
CREATE OR REPLACE FUNCTION createChampionship(int, text) RETURNS VOID AS
$$
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
$$
LANGUAGE 'plpgsql';



--
-- Generiert rekursiv alle Finalspiele
--
CREATE OR REPLACE FUNCTION generateKnockoutTree(int, bigint) RETURNS VOID AS
$$
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
$$
LANGUAGE 'plpgsql';














--
-- Erstellt ein DummyLand.
-- Falls es schon vorhanden ist wird nur dieses zurueckgegeben.
--
CREATE OR REPLACE FUNCTION getCountry() RETURNS SETOF Country AS
$$
DECLARE
	selectedRow Country%ROWTYPE;
	n int := 0;
BEGIN
	SELECT COUNT(*) INTO n FROM Country;
	IF(n < 1) THEN
		INSERT INTO Country VALUES (getNextSequence(), 'DummyLand');
	END IF;
	
	SELECT * INTO selectedRow FROM Country ORDER BY RANDOM() LIMIT 1;
	RETURN NEXT selectedRow;
END
$$ LANGUAGE 'plpgsql';











--
-- String Concatination Helper
--
CREATE OR REPLACE FUNCTION concat(VARCHAR, INT) RETURNS VARCHAR AS
$$
BEGIN
	return $1 || ' ' || chr(49 + ($2%119));
END
$$ language 'plpgsql';

--
-- String Concatination Helper
--
CREATE OR REPLACE FUNCTION concat(VARCHAR, BIGINT) RETURNS VARCHAR AS
$$
BEGIN
	return $1 || ' ' || chr(CAST(49 + ($2%119) AS INT));
END
$$ language 'plpgsql';












--
-- Gibt 8 Stadien (aus dem gegebenen Land) zurueck.
-- Falls nicht genuegend existieren werden welche erstellt.
--
CREATE OR REPLACE FUNCTION getStadiumsForCountry(bigint) RETURNS SETOF stadium AS
$$
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
$$ LANGUAGE 'plpgsql';






--
-- Hilfsfunktion um den Primaerschluessel fuer die Relationen zu ermitteln
--
CREATE OR REPLACE FUNCTION getNextSequence() RETURNS bigint AS
$$
	SELECT nextval('hibernate_sequence') FROM hibernate_sequence;
$$ LANGUAGE 'sql';








--
-- Erstellt ein Team mit 23 Spielern
--
CREATE OR REPLACE FUNCTION generateTeam() RETURNS team AS
$$
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
$$ LANGUAGE 'plpgsql';









--
-- Erstellt einen neuen Spieler
--
CREATE OR REPLACE FUNCTION getPlayer() RETURNS player AS
$$
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
$$ LANGUAGE 'plpgsql';





--
-- Erstellt alle Gruppenspiele fuer eine gegeben Gruppe
--
CREATE OR REPLACE FUNCTION generateGroupMatches(BIGINT) RETURNS VOID AS
$$
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
$$ LANGUAGE 'plpgsql';



--
-- Erstellt ein noch nicht gespieltes Gruppenspiel für zwei Mannschaften
--
CREATE OR REPLACE FUNCTION generateMatch(bigint, bigint, bigint) RETURNS VOID AS
$$
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
$$ LANGUAGE 'plpgsql';


--
-- Generiert die Gruppenphase
-- Es werden 8 Gruppen mit jeweils 4 Mannschaften erstellt
--
CREATE OR REPLACE FUNCTION generateGroupStage() RETURNS GroupStage AS
$$
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
$$ LANGUAGE 'plpgsql';

