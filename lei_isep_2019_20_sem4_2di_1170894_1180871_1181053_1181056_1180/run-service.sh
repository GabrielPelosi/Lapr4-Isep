#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.service/target/base.service-1.3.0-SNAPSHOT.jar:base.service/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP TcpSrv
