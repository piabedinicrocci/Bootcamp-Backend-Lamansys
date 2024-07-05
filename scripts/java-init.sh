#!/bin/sh

BASEDIR=$(dirname "$0")
cd "$BASEDIR/.."

mkdir back-end/

cd back-end

mvn archetype:generate -DgroupId=ar.lamansys -DartifactId=mensajes \
    -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
