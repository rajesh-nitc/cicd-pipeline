pipeline {
    agent {
        docker {
            image 'maven:3.6.3-jdk-11'
            args '-v /home/rajesh_nitc_gcp/.m2:/root/.m2'
        }
    }

    environment { 
      PUBLIC_IP = '34.93.172.88'
    }

    options {
        skipStagesAfterUnstable()
    }

    stages {  
        stage("BUILD: Unit Tests and Code Scan") {
            steps {
              withSonarQubeEnv('sonarserver') {
                sh 'mvn clean verify sonar:sonar -DskipITs=true'
              }
            }
          }

        stage("QUALITY: Quality Gate") {
            steps {
              sh 'pwd'
              timeout(time: 10, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }
          }

        stage('DEPLOY') {
            steps {
                sshagent ( ['bce2014e-8d55-4208-baca-577f76adcf0a']) {
                    sh 'scp -o StrictHostKeyChecking=no target/*.jar rajesh_nitc_gcp@"$PUBLIC_IP":/opt/app/target'
                    sh 'ssh -tt -o StrictHostKeyChecking=no -l rajesh_nitc_gcp $PUBLIC_IP "pwd; sudo supervisorctl restart javaapp"'
                }
            }
        }

        stage('FUNCTIONAL TESTS') {
            steps {
              sh 'wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -'
              sh 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
              sh 'apt-get update'
              sh 'apt-get install google-chrome-stable -y'
              sh 'apt-get install libgconf-2-4 -y'
              sh 'apt-get install unzip'
              sh 'wget -O /tmp/chromedriver.zip http://chromedriver.storage.googleapis.com/79.0.3945.36/chromedriver_linux64.zip'
              sh 'unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/'
              sh 'mvn clean verify -DskipUTs=true'
            }
        }

    }
    
    post { 
      always { 
        emailext attachmentsPattern: 'target/surefire-reports/*.txt', body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
        recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
        subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
        }
      }
}