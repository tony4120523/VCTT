#!/bin/sh
set -x
T='Test'
if [ -e $1.txt ] && [ -e $1.java ] && [ -e table.txt ]; then
    javac $1.java
    javac TestcaseGenerator.java
    java TestcaseGenerator $1 < $1.txt > $1$T.java
    javac -cp .:/usr/share/java/junit.jar $1$T.java
    java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore $1$T > junit_result.txt
    javac ReportGenerator.java
    java ReportGenerator $1 > report.txt
    Email=`cat report.txt | grep -E -o "\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}\b"`
    cat report.txt | mailx -v \
        -s "測試結果失敗" \
        -a $1.txt \
        -S smtp-use-starttls \
        -S ssl-verify=ignore \
        -S smtp-auth=login \
        -S smtp=smtp://smtp.gmail.com:587 \
        -S from="shmapp.report@gmail.com(Junit 測試通知)" \
        -S smtp-auth-user=shmapp.report@gmail.com \
        -S smtp-auth-password=0976118647 \
        -S ssl-verify=ignore \
        -S nss-config-dir=~/.mozilla/firefox/at5p7niw.default/ \
        $Email
    rm *.class
    rm $1$T.java
    rm junit_result.txt
    rm report.txt
    rm $1.txt
    rm $1.java
    rm table.txt
    exit 0
fi
echo "No txt or java file"
