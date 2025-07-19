pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }
    environment {
        NEXUS_REPO_URL = 'http://localhost:8081/repository/maven-releases/'
        SONAR_PROJECT_KEY = 'CRUDEtudiant_Using_SPRINGFramework'
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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY}'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    def jarFile = findFiles(glob: 'target/*.jar')[0].path
                    sh """
                        mvn deploy:deploy-file \
                        -DgroupId=com.example \
                        -DartifactId=crud-etudiant \
                        -Dversion=1.0.0 \
                        -Dpackaging=jar \
                        -Dfile=${jarFile} \
                        -Durl=${NEXUS_REPO_URL} \
                        -DrepositoryId=nexus-releases \
                        -DskipTests=true
                    """
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline succeeded! Artifact deployed to Nexus.'
        }
        failure {
            echo 'Pipeline failed! Check logs.'
        }
    }
}
