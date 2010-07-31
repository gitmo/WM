#!/bin/sh

set -x
set -o errexit

# To execute anything with maven from the shell make sure to set
# Otherwise any data written to the data base will have the wrong encoding.
export MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Use this alias cmd to load the stored procedures
alias loadproc="psql test < ./src/main/resources/dev/stored_procedures/createChampionship.sql"



# Unpacking sources if not existand
[ -d src/ ] || tar xvf sources.tar.gz


# Delete schema in DB test
read -p 'Are you sure to delete everything from local DB "test" now? (y/n): ' answer
[[ $answer != "y" ]] && exit 1

# Delete everything in DB test
echo 'DROP SCHEMA public CASCADE; CREATE SCHEMA public AUTHORIZATION postgres;'\
  | psql -U postgres -h localhost test

# Quick command line to empty the data base w/o dropping tables:
# echo 'truncate table team,player,tournament cascade;' | psql test


# This will export the schema
# mvn process-classes

# This will run everything (compile, export schema, test)
mvn test


# Load stored procedure now as the database is created
loadproc


# Here’s how to launch our GUI:
# mvn exec:java -Dexec.mainClass="dbs.project.main.gui.AppGui" 
# Or:
# java -Dfile.encoding=UTF-8 -jar release/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar

