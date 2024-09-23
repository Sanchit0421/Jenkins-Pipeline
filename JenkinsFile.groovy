pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // your build steps
            }
        }
    }

    post {
        failure {
            emailext (
                to: 'aggarwalsanchit2005@gmail.com',
                subject: "Jenkins Build Failed - ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """The build ${env.BUILD_NUMBER} failed.
                Check the logs at ${env.BUILD_URL}
                """,
                attachLog: true
            )
        }
    }
}
