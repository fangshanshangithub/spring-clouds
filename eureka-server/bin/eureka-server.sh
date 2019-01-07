#!/bin/bash
#
# chkconfig:   - 20 80
# description: Starts and stops the App.
# author:vakinge

#RUNNING_USER=vakinge
#ADATE=`date +%Y%m%d%H%M%S`
#环境变量【配置文件尾部名称】
#ENV=development
# 对应要执行的jar包名称
APP_NAME=eureka-server-0.0.1-SNAPSHOT

APP_HOME=`pwd`
dirname $0|grep "^/" >/dev/null
if [ $? -eq 0 ];then
   APP_HOME=`dirname $0`
else
    dirname $0|grep "^\." >/dev/null
    retval=$?
    if [ $retval -eq 0 ];then
        APP_HOME=`dirname $0|sed "s#^.#$APP_HOME#"`
    else
        APP_HOME=`dirname $0|sed "s#^#$APP_HOME/#"`
    fi
fi

if [ ! -d "$APP_HOME/log" ];then
  mkdir $APP_HOME/log
fi

LOG_PATH=$APP_HOME/log/$APP_NAME.out
#GC_LOG_PATH=$APP_HOME/logs/gc-$APP_NAME-$ADATE.log
#JMX监控需用到
#JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1091 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#JVM参数
#JVM_OPTS="-Dname=$APP_NAME -Djeesuite.configcenter.profile=$ENV -Duser.timezone=Asia/Shanghai -Xms512M -Xmx512M -XX:PermSize=256M -XX:MaxPermSize=512M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$GC_LOG_PATH -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"


JAR_FILE=jar/$APP_NAME.jar

pid=0
start(){
  checkpid
  if [ ! -n "$pid" ]; then
    #JAVA_CMD="nohup java -jar $JVM_OPTS $JAR_FILE > $LOG_PATH 2>&1 &"
    #su - $RUNNING_USER -c "$JAVA_CMD"
    nohup java -jar $JAR_FILE > $LOG_PATH 2>&1 &
    echo "---------------------------------"
    echo "启动完成，按CTRL+C退出日志界面即可>>>>>"
    echo "---------------------------------"
    sleep 3s
    checkpid
    echo "项目对应的进程 PID为: $pid"
    tail -f $LOG_PATH
  else
      echo "$APP_NAME is runing PID: $pid"
  fi

}


status(){
   checkpid
   if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
   else
     echo "$APP_NAME runing PID: $pid"
   fi
}

checkpid(){
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
    else
      echo "项目:$APP_NAME 正在运行,对应的进程 PID为:$pid,现在开始stop..."
      kill -9 $pid

      checkpid
      echo "$APP_NAME stop success  PID--$pid"
    fi
}

restart(){
    stop
    sleep 1s
    start
}

case $1 in
          start) start;;
          stop)  stop;;
          restart)  restart;;
          status)  status;;
              *)  echo "require start|stop|restart|status"  ;;
esac