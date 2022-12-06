#! /bin/bash

ssh quicktionary-backend.local << EOF
    cd quicktionary-backend
    git pull origin master
    ./bin/stop.sh
EOF