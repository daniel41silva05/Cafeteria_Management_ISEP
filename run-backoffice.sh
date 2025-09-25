#!/bin/bash

# Define the classpath
CAFETERIA_CP="cafeteria.app.backoffice.console\target\app.backoffice.console-0.1.0.jar;cafeteria.app.backoffice.console\target\dependency\*"

# Call the java VM
java -cp $CAFETERIA_CP lapr4.cafeteria.app.backoffice.console.CafeteriaBackoffice

# Pause the script (optional, for debugging purposes)
read -p "Press any key to continue... " -n1 -s
