pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "The process by which the code is compiled, built, and packaged in a deployable format."
                echo 'Maven – A popular build automation tool for Java projects that manages project dependencies and builds the application.'
            }
        }
        stage('Test') {
            steps {
                echo 'The process of unit tests to verify the functionality of components in isolation. JUnit – An open-source framework for unit testing in Java.'
                echo 'Integration tests ensure different software components work together. Katalon Platform – A widely used tool for integration testing.'
            }
            post {
                success {
                    emailext attachLog: true, body: 'Test was successful!', subject: 'Test Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
                failure {
                    emailext attachLog: true, body: 'Test failed!', subject: 'Test Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Analyzing code to ensure it meets quality standards. SonarQube – An open-source platform for continuous code quality inspection.'
            }
        }
        stage('Security Scan') {
            steps {
                echo 'Conducting security testing to identify vulnerabilities. OpenVAS – A popular open-source tool for vulnerability detection.'
            }
            post {
                success {
                    emailext attachLog: true, body: 'Security Scan was successful!', subject: 'Security Scan Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
                failure {
                    emailext attachLog: true, body: 'Security Scan failed!', subject: 'Security Scan Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying the application to a staging environment for testing.'
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                echo "Running integration tests in the staging environment."
            }
        }
        stage('Deploy to Production') {
            steps {
                echo "Deploying the application to the production environment."
            }
            post {
                success {
                    emailext attachLog: true, body: 'Deployment to Production was successful!', subject: 'Deploy to Production Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
                failure {
                    emailext attachLog: true, body: 'Deployment to Production failed!', subject: 'Deploy to Production Status Email', to: 'aggarwalsanchit2005@gmail.com'
                }
            }
        }
    }
}
