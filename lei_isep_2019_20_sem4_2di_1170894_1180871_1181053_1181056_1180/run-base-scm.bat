REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_SRV=base.scm\target\base.scm-1.3.0-SNAPSHOT.jar;base.scm\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_SRV% TcpClient