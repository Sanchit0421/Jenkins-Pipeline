pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo 'Building project to compile and package using Maven'
                // Uncomment the following line to run the build
                // sh 'mvn clean package'
            }
        }

        stage("Unit and Integration Tests") {
            steps {
                echo 'Running JUnit tests to verify code functionality.'
                // Uncomment the following line to run unit tests
                // sh 'mvn test'
                
                echo 'Executing integration tests to ensure components work together.'
                // Uncomment the following line to run integration tests
                // sh 'mvn verify'
            }
            post {
                success {
                    mail to: "aggarwalsanchit2005@gmail.com",
                         subject: "Success: JUnit and Integration tests successful.",
                         body: "JUnit and integration tests have passed successfully."
                }
                failure {
                    mail to: "aggarwalsanchit2005@gmail.com",
                         subject: "Failure: JUnit and Integration tests failure.",
                         body: "JUnit and integration tests have failed. Please review the logs."
                }
            }
        }

        stage("Code Analysis") {
            steps {
                echo 'Performing code analysis using SonarQube'
                // Uncomment the following line to run SonarQube analysis
                // sh 'mvn sonar:sonar'
            }
        }

        stage("Security Scan") {
            steps {
                echo 'Conducting security scan using OWASP ZAP'
                // Uncomment the following line to run the security scan
                // sh 'zap-cli quick-scan --self-contained --start-options "-config api.disablekey=true" http://localhost:8080'
            }
            post {
                success {
                    emailext(
                        
                         subject: "Success: Security scan successful.",
                         body: '''<html>
                                     <body>
                         <p>Build Status: ${BUILD_STATUS}</p>
                    <p>Check the <a href="${BUILD_URL}">console output</a?.</p>)
                        </body>
                        <html>''',
                        to: 'aggarwalsanchit2005@gmail.com',
                        from 'jenkins@example.com',
                        replyTo: 'jenkins@example.com',
                        mimeType: 'text/html'
                        )
                }
                failure {
                    mail to: "aggarwalsanchit2005@gmail.com",
                         subject: "Failure: Security scan failed.",
                         body: "The security scan has failed. Please review and take necessary actions."
                }
            }
        }

        stage("Deploy to Staging") {
            steps {
                echo 'Deploying to staging server (AWS EC2) at s3://staging-bucket/'
                // Uncomment the following line to deploy to staging
                // sh 'aws deploy create-deployment --application-name my-app --deployment-group-name staging-group --s3-location bucket=staging-bucket,key=my-app.zip'
            }
        }

        stage("Integration Tests on Staging") {
            steps {
                echo 'Running integration tests in the staging environment'
                // Uncomment the following line to run integration tests on staging
                // sh 'mvn verify -Dtest=IntegrationTest'
            }
        }

        stage("Deploy to Production") {
            steps {
                echo 'Deploying to production server (AWS EC2)'
                // Uncomment the following line to deploy to production
                // sh 'aws deploy create-deployment --application-name my-app --deployment-group-name production-group --s3-location bucket=production-bucket,key=my-app.zip'
            }
        }

        stage("Complete") {
            steps {
                echo 'Deployment process completed'
            }
        }
    }

    post {
        success {
            echo 'Deployment to production was successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
