pipeline {
    agent {
        docker {
            image 'feldicskobalazs/maven_docker:v1'
            args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'jenkins_cicd', credentialsId: 'github', url: 'git@github.com:ImreFekete/stackoverflow-tw.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'ls -ltr'
                sh 'cd backend && mvn clean package'
            }
        }
        stage('Static Code Analysis') {
            environment {
                SONAR_URL = 'http://172.20.0.4:9000'
            }
            steps {
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
                    sh 'cd backend && mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
                }
            }
        }
        stage('Build and Push Docker Image') {
            environment {
                DOCKER_IMAGE = "feldicskobalazs/javatest:${currentBuild.number}"
                DOCKERFILE_LOCATION = 'stackoverflow++/backend.Dockerfile'
                REGISTRY_CREDENTIALS = credentials('dockerhub')
            }
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
