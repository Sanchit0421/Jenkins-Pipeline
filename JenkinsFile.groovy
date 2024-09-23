pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building project to compile and package using Maven'
                // Your Maven build command here
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo 'Running JUnit tests to verify code functionality.'
                // Your JUnit test command here

                // Send email with logs
                emailext(
                    to: 'aggarwalsanchit2005@gmail.com',
                    subject: "JUnit Test Results: ${currentBuild.fullDisplayName}",
                    body: "JUnit tests have been executed. Please find the logs attached.",
                    attachLog: true
                )
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis using SonarQube'
                // Your SonarQube command here
            }
        }
        stage('Security Scan') {
            steps {
                echo 'Conducting security scan using OWASP ZAP'
                // Your security scan command here

                // Send email with logs
                emailext(
                    to: 'aggarwalsanchit2005@gmail.com',
                    subject: "Security Scan Results: ${currentBuild.fullDisplayName}",
                    body: "Security scan has been completed. Please find the logs attached.",
                    attachLog: true
                )
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to staging server (AWS EC2) at s3://staging-bucket/'
                // Your deployment command here
            }
        }
        stage('Deploy to Production') {
            steps {
                echo 'Deploying to production server (AWS EC2)'
                // Your production deployment command here
            }
        }
    }
    post {
        always {
            echo 'Deployment process completed!'
        }
        success {
            echo 'Deployment to production was successful!'
        }
        failure {
            echo 'Deployment to production failed!'
        }
    }
}

