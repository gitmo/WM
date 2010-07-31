#!/bin/sh -xe

export MAVEN_OPTS="-Dfile.encoding=UTF-8"

# In linux there is no .Trash folder (for gnome there is ~/.local/share/Trash)
if [ -d ~./Trash ]
then
	mv release/ ~/.Trash/release-$$
else
	rm -r release
fi

mkdir release/

# This creates 3 JARs for sources, test sources and classes.
# A single JAR would be nice. But this is easier for now 

# Clean
mvn clean

# Sources
mvn source:jar
cp target/WM-0.0.1-SNAPSHOT-sources.jar release/

# Test sources
mvn source:test-jar
cp target/WM-0.0.1-SNAPSHOT-test-sources.jar release/

# Runnable jar
mvn -DskipTests=true assembly:assembly
cp target/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar release/

cp -rp doc/ release/
cp -p runtests.sh release/
cp -p pom.xml release/

# ZIP content
cd release
zip -r Konrad_vonGeysoHoeffkenRaitza .
cd ..

# Test
java -jar release/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar
