pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'springboot-app'
        DOCKER_IMAGE_TAG = 'latest'
        SONARQUBE_URL = 'http://sonarqube:9000'
        SONARQUBE_TOKEN = credentials('sonarqube-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/fr1bze/laba4_project_spring.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }

        stage('Static Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh '''
                        sonar-scanner \
                        -Dsonar.projectKey=your-project-key \
                        -Dsonar.sources=src \
                        -Dsonar.host.url=$SONARQUBE_URL \
                        -Dsonar.login=$SONARQUBE_TOKEN
                        '''
                    }
                }
            }
        }

        stage("Docker Build") {
            steps {
                sh "docker build -t user/springboot-app ./springboot-app"
            }
        }

        stage("Docker compose up") {
            steps {
                sh "docker-compose up -d"
            }
        }
    }

    post {
        always {
            sh 'docker system prune -f'
        }
    }
}