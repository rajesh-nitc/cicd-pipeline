## CI/CD Pipeline
CI/CD pipeline for a springboot application, the deployment environment is a single instance

## Getting Started
### Jenkins
```
docker run \
  --rm \
  -u root \
  -p 8080:8080 \
  -v jenkins-data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v "$HOME":/home \
  -d \
  jenkinsci/blueocean
```
### Sonarqube
```
docker run -d --name sonarqube -p 9000:9000 sonarqube
```
Setup sonarqube server side:
Get user token ```User/My Account/Security/Generate Tokens``` \
Get webhook for quality gate ```Administration/Configuration/Webhooks/Create http://{JENKINS_HOST}/sonarqube-webhook/```

Setup sonarqube jenkins side:
Install sonarqube scanner plugin \
Setup sonarqube server ```Manage Jenkins/Configure System/SonarQube servers``` \
Setup sonarqube scanner ```Manage Jenkins/Global Tool Configuration/SonarQube Scanner```

### Deploy
Create ssh keys for the rajesh_nitc_gcp user:
```
ssh-keygen -t rsa -C "The access key for jenkins"
cat ~/.ssh/id_rsa.pub > ~/.ssh/authorized_keys
cat ~/.ssh/id_rsa
```
Copy id_rsa i.e. private key to the jenkins credentials management \
Install ssh agent plugin \
Give permission to rajesh_nitc_gcp to scp the jar file in the target folder ```sudo chown -R rajesh_nitc_gcp /opt/app```

### Errors
Error: resolve sudo: no tty present and no askpass program specified
```
sudo visudo
rajesh_nitc_gcp ALL=(ALL) NOPASSWD: ALL
```
