# Herramientas

docker run -itv ${PWD}:/usr/src/app -w /usr/src/app maven:3.8-openjdk-18 ./back-end/build.sh

docker run -itv ${PWD}:/usr/src/app -w /usr/src/app maven:3.8-openjdk-18 ./back-end/run-tests.sh