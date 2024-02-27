pipeline {
    agent {
        docker {
            image 'abhishekf5/maven-abhishek-docker-agent:v1'
            args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Checkout') {
            steps {
            git branch: 'main', credentialsId: "github", url: 'https://github.com/iam-veeramalla/Jenkins-Zero-To-Hero.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'ls -ltr'
                // build the project and create a JAR file
                sh 'cd java-maven-sonar-argocd-helm-k8s/spring-boot-app && mvn clean package'
            }
        }
        stage('Static Code Analysis') {
            environment {
                SONAR_URL = 'http://34.201.116.83:9000'
            }
            steps {
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
                    sh 'cd java-maven-sonar-argocd-helm-k8s/spring-boot-app && mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
                }
            }
        }
    }
}
