pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Building the project"
            }
        }
    }
    post {
        success {
            emailext attachLog: true, body: 'Email sent from Jenkins', subject: 'Test Email- Success', to: 'aggarwalsanchit2005@gmail.com'
        }
        failure{
            emailext attachLog: true, body: 'Email sent from Jenkins', subject: 'Test Email- Success', to: 'aggarwalsanchit2005@gmail.com'
    }}
}
