#!/bin/bash -xe

apt-get update
apt-get install -yq openjdk-8-jdk maven git supervisor
mkdir /opt/app
cd /opt/app
git init
export HOME=/root
git config --global credential.helper gcloud.sh
git pull https://source.developers.google.com/p/tf-first-project/r/maven-jenkins
mvn clean package

# Create a javaapp user. The application will run as this user.
useradd -m -d /home/javaapp javaapp
chown -R javaapp:javaapp /opt/app

# Configure supervisor to run the java app.
cat >/etc/supervisor/conf.d/java-app.conf << EOF
[program:javaapp]
directory=/opt/app
command=java -jar target/springboot-first-0.0.1-SNAPSHOT.jar
autostart=true
autorestart=true
user=javaapp
stdout_logfile=syslog
stderr_logfile=syslog
EOF

supervisorctl reread
supervisorctl update