#!/bin/bash

tar -czf log-`date +%Y%m%d`.tar.gz /var/log

#crontab -u -e root
#00 18 * * 5 project3_2.sh
