pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven3'
    }

    stages {
        stage('Clean') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean"
            }
        }
        stage('Compile & Test') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn install"
            }
        }
        stage('Run Unit Tests') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }
        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('Jenkins-sonar-token')
            }
            steps {
                sh "${MAVEN_HOME}/bin/mvn sonar:sonar -Dsonar.login=${SONAR_TOKEN}"
            }
        }
        stage('Package') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn package"
            }
        }
        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    writeFile file: 'settings.xml', text: """
                        <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
                          <servers>
                            <server>
                              <id>nexus-releases</id>
                              <username>${NEXUS_USER}</username>
                              <password>${NEXUS_PASS}</password>
                            </server>
                          </servers>
                        </settings>
                    """
                    sh "${MAVEN_HOME}/bin/mvn deploy --settings settings.xml"
                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        success {
            echo 'Build, Test, Sonar, and Deploy succeeded!'
        }
        failure {
            echo 'Build failed, check logs!'
        }
    }
}
