pipeline {
    agent any
    tools {
        maven 'Maven-3.9.9' // Use the Maven installation configured in Jenkins
    }
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
                bat 'mvn clean install' // Use 'bat' for Windows
            }
        }
        stage('Test') {
            steps {
                echo 'Running Tests...'
                bat 'mvn test' // Use 'bat' for Windows
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging the application...'
                bat 'mvn package' // Use 'bat' for Windows
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
