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
                stage('Test 1') {
                    steps {
                        echo 'Running Test 1...'
                        bat 'mvn test -Dtest=TestClass1'
                    }
                }
                stage('Test 2') {
                    steps {
                        echo 'Running Test 2...'
                        bat 'mvn test -Dtest=TestClass2'
                    }
                }
                stage('Test 3') {
                    steps {
                        echo 'Running Test 3...'
                        bat 'mvn test -Dtest=TestClass3'
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
