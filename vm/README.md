## vm

===
containers:

docker run -d --name sonarqube -p 9000:9000 sonarqube
docker run \
  --rm \
  -u root \
  -p 8080:8080 \
  -v jenkins-data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v "$HOME":/home \
  -d \
  jenkinsci/blueocean

===
SonarQube:

SonarQube server side:
user token User/My Account/Security/Generate Tokens
webhook for quality gate Administration/Configuration/Webhooks/Create http://{JENKINS_HOST}/sonarqube-webhook/

SonarQube jenkins side:
Install SonarQube Scanner plugin
Setup SonarQube server Manage Jenkins/Configure System/SonarQube servers Copy the name
Setup SonarQube scanner Manage Jenkins/Global Tool Configuration/SonarQube Scanner Copy the name

===
Deploy:

create ssh keys for the rajesh_nitc_gcp user:
ssh-keygen -t rsa -C "The access key for jenkins"
cat ~/.ssh/id_rsa.pub > ~/.ssh/authorized_keys
cat ~/.ssh/id_rsa
COPY id_rsa i.e. private key to the jenkins credentials management

install ssh agent plugin

give permission to rajesh_nitc_gcp to scp the jar file in the target folder:
sudo chown -R rajesh_nitc_gcp /opt/app

sudo: no tty present and no askpass program specified' error?:
if sudo command is being used in the cicd pipeline, setting below command is must
sudo visudo
rajesh_nitc_gcp ALL=(ALL) NOPASSWD: ALL
===

http://35.200.170.8/