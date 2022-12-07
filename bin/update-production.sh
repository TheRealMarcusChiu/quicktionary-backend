#! /bin/bash

ssh quicktionary << EOF
    cd quicktionary-backend
    git pull origin master
    ./bin/stop.sh
EOF