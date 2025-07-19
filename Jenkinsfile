pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven 3'  // Or your configured Maven name
    }

    stages {
        stage('Build and Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh """
                        ${MAVEN_HOME}/bin/mvn clean deploy \
                        -Dusername=${NEXUS_USER} \
                        -Dpassword=${NEXUS_PASS}
                    """
                }
            }
        }
    }
}
