#!/bin/bash
. ./repoLoggerConfig.sh
RUNDIR=`pwd`
CONTAINER_DIR=/app
echo extracting from $RUNDIR
export CONF=$RUNDIR/conf
export CONFSECURE=$RUNDIR/confsecure
export ZIPKINIP=`docker inspect --type container -f '{{.NetworkSettings.IPAddress}}' zipkin`
docker run  --add-host zipkin:$ZIPKINIP --publish  8082:8082 --publish  9082:9082 --rm  --volume $CONF:/$CONTAINER_DIR/conf --name logger  $REPO:0.0.1
