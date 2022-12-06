#! /bin/bash

ssh quicktionary-backend.local << EOF
    cd quicktionary-backend
    git pull origin master
    ./bin/deploy/kill.sh
# systemd redeploys
#    nohup mvn spring-boot:run &
#    tail -f nohup.out
EOF