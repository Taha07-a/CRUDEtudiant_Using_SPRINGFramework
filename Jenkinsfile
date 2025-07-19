pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'jdk17'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: .master.,
                url: 'https://github.com/YOUR_USER/CRUDEtudiant_Using_SPRINGFramework.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Nexus Deploy') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}
