pipeline {
    agent any
    stages {
        stage('Test Email') {
            steps {
                script {
                    emailext to: 'aggarwalsanchit2005@gmail.com',
                             subject: 'Test Email',
                             body: 'This is a test email from Jenkins.'
                }
            }
        }
    }
}
