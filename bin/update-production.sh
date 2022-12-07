#! /bin/bash

ssh 192.168.86.6 << EOF
    cd quicktionary-backend
    git pull origin master
    ./bin/stop.sh
EOF