#!/bin/sh
export MAVEN_OPTS="-Dfile.encoding=UTF-8"

mvn -DskipTests=true assembly:assembly
java -jar /Users/sebrz/Code/eclipse/WM/target/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar
