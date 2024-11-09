pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning Repository...'
                git url: 'https://github.com/tsdotinc/employee-management-api.git', branch: 'main'
            }
        }

        stage('Build and Test in Parallel') {
            parallel {
                stage('Build') {
                    steps {
                        echo 'Building the Project...'
                        sh 'mvn clean install'
                    }
                }

                stage('Test') {
                    steps {
                        echo 'Running Tests...'
                        sh 'mvn test'
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the Application...'
                sh 'mvn package'
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
