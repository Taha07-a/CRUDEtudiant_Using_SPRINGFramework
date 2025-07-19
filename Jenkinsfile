pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK17' 
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                url: 'https://github.com/Taha07-a/CRUDEtudiant_Using_SPRINGFramework.git'
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
                sh 'mvn sonar:sonar -Dsonar.projectKey=CRUDEtudiant_Using_SPRINGFramework'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
