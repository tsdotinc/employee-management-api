pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/tsdotinc/employee-management-api.git'
        GITHUB_CREDENTIALS = 'github-token' // Make sure this corresponds to your Jenkins credentials ID
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning Repository...'
                git url: "${GIT_REPO}", credentialsId: "${GITHUB_CREDENTIALS}"
            }
        }

        stage('Build and Test in Parallel') {
            parallel {
                stage('Build') {
                    steps {
                        echo 'Building the Project...'
                        sh 'mvn clean install'  // Using mvn since Maven is installed on Jenkins
                    }
                }

                stage('Test') {
                    steps {
                        echo 'Running Tests...'
                        sh 'mvn test'  // Using mvn test command
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the Application...'
                sh 'mvn package'  // Packaging the application
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the Application...'
                // Add your deployment steps here (e.g., copy files to a server, deploy to AWS, etc.)
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()  // Cleans the workspace to ensure no leftover files from previous builds
        }
    }
}
