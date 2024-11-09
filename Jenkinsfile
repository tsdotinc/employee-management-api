pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning Repository...'
                git url: 'https://github.com/tsdotinc/employee-management-api.git'
            }
        }

        stage('Build and Test in Parallel') {
            parallel {
                stage('Build') {
                    steps {
                        echo 'Building the Project...'
                        sh './mvnw clean install'
                    }
                }

                stage('Test') {
                    steps {
                        echo 'Running Tests...'
                        sh './mvnw test'
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the Application...'
                sh './mvnw package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the Application...'
                // Add your deployment steps here
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
