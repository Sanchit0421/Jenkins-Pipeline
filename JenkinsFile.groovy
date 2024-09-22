pipeline {
    agent any 

    stages {
        stage('Build') {
            steps {
                script {
                    echo 'Building the code using Maven...'
                    // Command to build the project using Maven
                    sh 'mvn clean package'
                }
            }
        }

        stage('Unit and Integration Tests') {
            steps {
                script {
                    echo 'Running unit and integration tests using maven...'
                   
                    sh 'mvn test'
                  
                    // sh 'newman run collection.json'
                }
            }
        }

        stage('Code Analysis') {
            steps {
                script {
                    echo 'Analyzing code quality...'
                    // Command to run SonarQube analysis
                    // sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Security Scan') {
            steps {
                script {
                    echo 'Performing security scan...'
                    // Command to run OWASP ZAP scan
                    // sh 'zap.sh -quickurl http://your-app-url'
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                script {
                    echo 'Deploying to staging...'
                    // Command to deploy to AWS or use Ansible
                    // sh 'ansible-playbook deploy-staging.yml'
                }
            }
        }

        stage('Integration Tests on Staging') {
            steps {
                script {
                    echo 'Running integration tests on staging...'
                    // Command to run integration tests in staging
                    // sh 'newman run staging-collection.json'
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                script {
                    echo 'Deploying to production...'
                    // Command to deploy to production server
                    // sh 'ansible-playbook deploy-production.yml'
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Pipeline completed successfully!'
                // Configure email notification
                emailext (
                    subject: "SUCCESS: ${currentBuild.fullDisplayName}",
                    body: "The build was successful.",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']]
                )
            }
        }
        failure {
            script {
                echo 'Pipeline failed.'
                // Configure email notification
                emailext (
                    subject: "FAILURE: ${currentBuild.fullDisplayName}",
                    body: "The build has failed. Check the logs.",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']]
                )
            }
        }
    }
}
