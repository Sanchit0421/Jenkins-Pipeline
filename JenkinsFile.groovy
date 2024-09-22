pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo 'Building project using Maven...'
                // sh 'mvn clean package'
            }
        }

        stage("Unit Tests") {
            steps {
                echo 'Running unit tests...'
                // sh 'mvn test'
            }
            post {
                always {
                    emailext (
                        to: "aggarwalsanchit2005@gmail.com",
                        subject: "Unit Tests Completed",
                        body: "The unit tests have completed. Please find the logs attached.",
                        attachLog: true
                    )
                }
            }
        }
        
        stage("Integration Tests") {
            steps {
                echo 'Running integration tests...'
                // sh 'mvn verify'
            }
            post {
                always {
                    emailext (
                        to: "aggarwalsanchit2005@gmail.com",
                        subject: "Integration Tests Completed",
                        body: "The integration tests have completed. Please find the logs attached.",
                        attachLog: true
                    )
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

