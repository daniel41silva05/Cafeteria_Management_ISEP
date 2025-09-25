#!/bin/bash

# Define the classpath
CAFETERIA_CP="cafeteria.app.bootstrap.console\target\app.bootstrap.console-0.1.0.jar;cafeteria.app.bootstrap.console\target\dependency\*"

# Call the java VM
java -cp $CAFETERIA_CP lapr4.cafeteria.app.backoffice.console.CafeteriaBootstrap

# Pause the script (optional, for debugging purposes)
read -p "Press any key to continue... " -n1 -s
