#! /bin/bash

kill $(ps aux | grep java | grep quicktionary-backend | awk  '{print $2}')