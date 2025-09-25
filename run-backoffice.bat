REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET CAFETERIA_CP=cafeteria.app.backoffice.console\target\app.backoffice.console-0.1.0.jar;cafeteria.app.backoffice.console\target\dependency\*;

REM call the java VM, e.g,
java -cp %CAFETERIA_CP% lapr4.cafeteria.app.backoffice.console.CafeteriaBackoffice

pause