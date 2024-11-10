pipeline {
    agent any
    tools {
        jdk 'jdk-17.0.7'
        maven 'Maven-3.9.2'
    }
    environment {
        JAVA_HOME = 'C:\\java_setup\\jdk-17.0.7'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/tsdotinc/employee-management-api'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
            }
        }
        stage('Test') {
    parallel {
        stage('Unit Tests') {
            steps {
                echo 'Running Unit Tests...'
                bat 'mvn test -Dtest=EmployeeManagementApiApplicationTests'
            }
        }
        stage('Integration Tests') {
            steps {
                echo 'Running Integration Tests...'
                bat 'mvn test -Dtest=EmployeeIntegrationTests'
            }
        }
    }
}
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
