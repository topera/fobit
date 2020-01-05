pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }

    environment {
        DOCKER_USER = 'put-my-user-here'
        DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
        AWS_GRADLE_USER="ubuntu"
        AWS_GRADLE_HOST="ec2-put-my-host-here.compute-1.amazonaws.com"
        AWS_GRADLE_KEY_PATH="/home/main.pem"
    }

    stages {

        stage('test') {
            steps {
                sh './gradlew runClean createVersion test'
            }
        }

        stage('createImage') {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew runCreateImage'
            }
        }

        stage('publishImage') {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew dockerLogin publishImage'
            }
        }

        stage('deploy') {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew fobitDeploy'
            }
        }
    }
}
