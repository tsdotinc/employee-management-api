pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning Repository...'
                git branch: 'main', url: 'https://github.com/tsdotinc/employee-management-api'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvnw.cmd clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running Tests...'
                bat 'mvnw.cmd test'
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging the application...'
                bat 'mvnw.cmd package'
            }
        }
    }
    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
