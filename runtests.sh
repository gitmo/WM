#!/bin/sh

# Use this alias cmd to load the stored procedures
alias loadproc="psql test < ./doc/sql/createChampionship.sql"

read -p 'Are you sure to delete everything from local DB "test" now? (y/n): ' answer
[[ $answer != "y" ]] && exit 1

# Delete everything in DB test
echo 'DROP SCHEMA public CASCADE; CREATE SCHEMA public AUTHORIZATION postgres;'\
  | psql test

# Quick command line to empty the data base w/o dropping tables:
# echo 'truncate table team,player,tournament cascade;' | psql test

# To execute anything with maven from the shell make sure to set
# Otherwise any data written to the data base will have the wrong encoding.
export MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Here’s how to launch our GUI:
# mvn exec:java -Dexec.mainClass="dbs.project.main.gui.AppGui" 

# This will export the schema
# mvn process-classes

# This will run everything (compile, export schema, run AppGui)
mvn test

# Load stored procedure now as the database is created
loadproc

#java -jar WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar