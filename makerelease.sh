#!/bin/bash

set -x
set -o errexit

export MAVEN_OPTS="-Dfile.encoding=UTF-8"
export JAVA_OPTS=$MAVEN_OPTS

ZIP=Konrad_vonGeysoHoeffkenRaitza

# In linux there is no .Trash folder (for gnome there is ~/.local/share/Trash)
if [ -d release ]; then
	! [ -d ~./Trash ] && mv release/ ~/.Trash/release-$$ || rm -r release
fi

mkdir release/

# This creates 3 JARs for sources, test sources and classes.
# A single JAR would be nice. But this is easier for now 

# Clean
mvn clean

# Sources
# mvn source:jar
# cp target/WM-0.0.1-SNAPSHOT-sources.jar release/

# Test sources
# mvn source:test-jar
# cp target/WM-0.0.1-SNAPSHOT-test-sources.jar release/

# Runnable jar
mvn -DskipTests=true assembly:assembly
cp -p target/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar release/

# Sources
tar -vcf release/sources.tgz src/ pom.xml runtests.sh

mkdir release/doc
cp -rp doc/tex/*pdf release/doc
cp -rp doc/sql release/doc
cp -rp doc/diagrams release/doc
cp -p doc/namen.txt release/

# ZIP content
rm -f "$ZIP.zip"
mv release "$ZIP"
zip -r "$ZIP" "$ZIP"
mv "$ZIP" release
zip -T "$ZIP"

# Test
java $JAVA_OPTS -jar release/WM-0.0.1-SNAPSHOT-jar-with-dependencies.jar
