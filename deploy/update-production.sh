#! /bin/bash

ssh quicktionary-backend.local << EOF
    cd quicktionary-backend
    git pull origin master
    ./bin/deploy/stop.sh
# systemd redeploys
#    nohup ./mvnw spring-boot:run &
#    tail -f nohup.out
EOF