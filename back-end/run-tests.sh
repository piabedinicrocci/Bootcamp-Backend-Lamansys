#!/bin/sh
set -o errexit

BASEDIR=$(dirname "$0")
cd "$BASEDIR"

MVN_CACHE=$(pwd)/.maven
export MAVEN_OPTS="$MAVEN_OPTS -Dmaven.repo.local=${MVN_CACHE}"
echo "MAVEN_OPTS set as '${MAVEN_OPTS}'"

cd mensajes
mvn test
