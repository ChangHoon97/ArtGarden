#!/bin/bash

mkdir -p "/home/ubuntu/logs/$(date '+%Y-%m')"
LOG_DIR="/home/ubuntu/logs/$(date '+%Y-%m')"
LOG_FILE="$LOG_DIR/$(date '+%Y-%m-%d').log"

cp "/home/ubuntu/logs/logfile.log" "$LOG_FILE" # 기존 로그를 오늘 날짜로 저장
echo ' ' > "/home/ubuntu/logs/logfile.log" # 기존 로그는 비우기
echo "$(date '+%Y-%m-%d'), success." >> "$LOG_DIR/log_list.txt"