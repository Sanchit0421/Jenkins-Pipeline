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
                echo 'Running unit tests...'
                // sh 'mvn test'
            }
            post {
                always {
                    script {
                        // Use a workaround to capture logs from the console output
                        def log = currentBuild.rawBuild.getLog(100) // Gets the last 100 lines of the log
                        mail to: "aggarwalsanchit2005@gmail.com",
                             subject: "Unit Tests Results",
                             body: "Unit tests have been executed.\n\nLogs:\n${log.join('\n')}"
                    }
                }
            }
        }

        // Add other stages as needed...

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



