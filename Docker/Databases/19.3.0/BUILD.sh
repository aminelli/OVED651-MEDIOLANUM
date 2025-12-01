#!/bin/bash

# docker build --force-rm=true   --target base -t aminelli/oracledb:19.3.0 -f Dockerfile .
# docker build --force-rm=true --no-cache=true   --build-arg DB_EDITION=ee -t aminelli/oracledb:19.3.0 -f Dockerfile .


# ENTERPRISE EDITION
# docker build --force-rm=true --no-cache=true   --build-arg DB_EDITION=ee -t aminelli/oracledb:19.3.0_ee -f Dockerfile .

# STANDARD EDITION
docker build --force-rm=true --no-cache=true   --build-arg DB_EDITION=se2 -t aminelli/oracledb:19.3.0_se2 -f Dockerfile .

# ESEMPIO DI UTILIZZO
# docker run --name customscripts \
# -p 1521:1521 -p 5500:5500 \
# -e ORACLE_SID=ORCLSCRIPT \
# -e ORACLE_PDB=CUSTOMSCRIPTS \
# -v /home/oracle/oradata:/opt/oracle/oradata \
# -v /home/oracle/docker/samples/customscripts:/opt/oracle/scripts/setup \
# oracle/database:12.2.0.1-ee

# CHANGE PASSWORD
# docker exec <container name> ./setPassword.sh <your password>
# Questo esegue il comando:
# sqlplus / as sysdba << EOF
#       ALTER USER SYS IDENTIFIED BY "$ORACLE_PWD";
#       ALTER USER SYSTEM IDENTIFIED BY "$ORACLE_PWD";
#       ALTER SESSION SET CONTAINER=$ORACLE_PDB;
#       ALTER USER PDBADMIN IDENTIFIED BY "$ORACLE_PWD";
#       exit;
# EOF

# After the container is up and running you can connect to the new database.
# Remember that the database uses an automatically generated password for the admin accounts.
# If you want to change the password refer to [Changing the admin accounts passwords](https://github.com/gvenzl/docker-images/tree/master/OracleDatabase#changing-the-admin-accounts-passwords):
# 
# 	sql system/<your new db password>@//localhost:1521/<your new SID/PDB name>