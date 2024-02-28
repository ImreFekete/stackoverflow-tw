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
                    sh 'docker build -t ${DOCKER_IMAGE} -f backend.Dockerfile .'
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Update Deployment File') {
            environment {
                TARGET_REPO_NAME = 'ImreFekete/stackoverflow-tw'
                TARGET_BRANCH = 'jenkins_cicd'
            }
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'github', keyFileVariable: 'SSH_PRIVATE_KEY', passphraseVariable: 'SSH_PASSPHRASE')]) {
                    sh '''
            git config user.email "feldicsko.balazs@gmail.com"
            git config user.name "feldicskobalazs"
            BUILD_NUMBER=${currentBuild.number}
            sed -i "s/replaceImageTag/${BUILD_NUMBER}/g" backend/deployment.yml
            git add backend/deployment.yml
            git commit -m "Update deployment image to version ${BUILD_NUMBER}"
            GIT_SSH_COMMAND="ssh -i $SSH_PRIVATE_KEY -o StrictHostKeyChecking=no" git push git@github.com:${TARGET_REPO_NAME}.git HEAD:${TARGET_BRANCH}
        '''
                }
            }
        }
    }
}
