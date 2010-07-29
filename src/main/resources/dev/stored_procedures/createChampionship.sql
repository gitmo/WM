-- Function: createChampionship(year int, name text)

DROP FUNCTION createChampionship(year int, name text);

/**
 * Turnier anlegen: Es soll eine neues Turnier mitsamt Turnierplan angelegt
 * werden. Je nach Anzahl der Mannschaften müssen dazu entsprechende
 * Gruppen angelegt werden. Werden keine Informationen zu den teilnehmenden
 * Mannschaften bei Turniergenerierung mit angegeben, so wird ein
 * Turnierplan mit generischen Bezeichnungen (z.B. „Mannschaft A1“, „Sieger
 * Spiel 27“) gefüllt. Es liegt Ihnen frei, zusätzliche Informationen zu
 * dem Zeitrahmen des Turniers mitanzugeben oder die Spiele an zufälligen
 * Tagen zu generieren. Implementieren Sie dazu an geeigneter Stelle eine
 * Methode createChampionship(String name), die als Eingabe ggf.
 * zusätzlich eine Menge von Mannschaften erhält.
 **/
CREATE OR REPLACE FUNCTION createChampionship(year int, name text)
  RETURNS SETOF tournament_country AS
$BODY$
DECLARE
   host RECORD;
  stad RECORD;
BEGIN
  RAISE NOTICE 'Creating a new tournament';
 
  -- Create a new tournament entry
  -- DELETE FROM tournament WHERE year=$1;
  -- UPDATE "tournament" SET name=name WHERE year=year;
  INSERT INTO "tournament" VALUES (year, name);
  -- TODO: Exception message if already exists

  -- Set a random host country
  SELECT * FROM "country" INTO STRICT host ORDER BY RANDOM() LIMIT 1;
  INSERT INTO "tournament_country" VALUES (year, host.id);
  
  -- Set 8 random stadiums
  FOR stad in SELECT * FROM "stadium" ORDER BY RANDOM() LIMIT 8 LOOP
    RAISE NOTICE 'test %', stad.name;
    INSERT INTO "tournament_stadium" VALUES (year, stad.stadiumid);
  END LOOP;

  RETURN QUERY SELECT * FROM tournament_country;	
  RETURN;
END;
$BODY$
LANGUAGE 'plpgsql' STRICT VOLATILE;


DROP FUNCTION createGroupStage();

CREATE OR REPLACE FUNCTION createGroupStage()
  RETURNS void AS
$BODY$
DECLARE
  num_teams INTEGER;
  num_groups INTEGER;
  i INTEGER;
  host RECORD;
  stad RECORD;
BEGIN
  RAISE NOTICE 'Creating Group Stage';

  SELECT COUNT(*) INTO num_teams FROM "team";
  IF num_teams % 4 != 0 THEN
    RAISE EXCEPTION 'Hey, stop breaking my balls over here!';
  END IF;

  num_groups := 8;
  FOR i IN 1..num_groups LOOP
    raise notice 'bastardo';
  END LOOP;
 END;
$BODY$
LANGUAGE 'plpgsql' STRICT VOLATILE;


TRUNCATE tournament CASCADE;
TRUNCATE TABLE tournament_country, tournament_stadium	;
SELECT createChampionship(657, 'Fuckdschikistan');
SELECT createGroupStage();
