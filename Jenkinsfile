pipeline {
    agent any
    tools {
        maven 'Maven3'        // Matches your Maven tool name in Jenkins
        jdk 'JDK17'       // Matches your JDK tool name in Jenkins
    }
    environment {
        NEXUS_REPO_URL = 'http://localhost:8081/repository/maven-releases/'  // Update Nexus URL
        SONAR_PROJECT_KEY = 'CRUDEtudiant_Using_SPRINGFramework'                  // SonarQube project key
    }
    stages {
        // Stage 1: Checkout from Git
        stage('Checkout') {
            steps {
                git branch: 'master', 
                url: 'https://github.com/Taha07-a/CRUDEtudiant_Using_SPRINGFramework.git'
            }
        }

        // Stage 2: Build and Run Unit Tests
        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/*.xml'  // Publishes JUnit test results
            }
        }

        // Stage 3: SonarQube Analysis
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {          // Assumes SonarQube is configured in Jenkins
                    sh 'mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY}'
                }
            }
        }

        // Stage 4: Deploy to Nexus
        stage('Deploy to Nexus') {
            steps {
                sh '''
                    mvn deploy:deploy-file \
                    -DgroupId=com.example \
                    -DartifactId=crud-etudiant \
                    -Dversion=1.0.0 \
                    -Dpackaging=jar \
                    -Dfile=target/*.jar \
                    -Durl=${NEXUS_REPO_URL} \
                    -DrepositoryId=nexus-releases \
                    -DskipTests=true
                '''
            }
        }
    }

    // Post-build actions (optional)
    post {
        success {
            echo 'Pipeline succeeded! Artifact deployed to Nexus.'
        }
        failure {
            echo 'Pipeline failed! Check logs.'
        }
    }
}
