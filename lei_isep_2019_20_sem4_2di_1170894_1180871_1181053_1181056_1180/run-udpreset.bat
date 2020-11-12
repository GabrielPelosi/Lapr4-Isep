REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.smm\target\base.smm-1.3.0-SNAPSHOT.jar;base.smm\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% reset.Udp_Reset
