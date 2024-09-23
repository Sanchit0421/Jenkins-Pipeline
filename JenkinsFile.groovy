pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo 'Building project...'
                // sh 'mvn clean package'
            }
        }

        stage("Unit Tests") {
            steps {
                script {
                    echo 'Running unit tests...'
                    // Replace with actual test command and capture output
                    def result = sh(script: 'mvn test', returnStdout: true)
                    echo result
                    mail to: "aggarwalsanchit2005@gmail.com",
                         subject: "Unit Tests Results",
                         body: "Unit tests have been executed.\n\nLogs:\n${result}"
                }
            }
        }

        stage("Complete") {
            steps {
                echo 'Pipeline completed.'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}

}



