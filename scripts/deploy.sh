#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/artgarden/server
cd $REPOSITORY

APP_NAME=server
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

# 현재 시간을 기반으로 로그 파일 이름 생성
LOG_FILE="/home/ubuntu/logs/logfile_$(date '+%Y-%m-%d').log"

echo "> $JAR_PATH 배포"
# 이전 로그 파일이 삭제되지 않도록 로그 파일 경로를 추가하여 백그라운드에서 실행
nohup java -jar -Dspring.profiles.active=prod $JAR_PATH >> $LOG_FILE 2>&1 &

# 이전 로그 파일이 삭제되지 않도록 해당 날짜의 로그 파일을 심볼릭 링크로 연결
ln -sf $LOG_FILE /home/ubuntu/logs/logfile.log

echo "> 서버가 시작되었습니다. 로그 파일: $LOG_FILE"

