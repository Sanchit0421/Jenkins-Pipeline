pipeline {
agent any
stages {
stage("Build") {
steps {
echo 'Building project to compile and package using Maven'
// sh 'mvn clean package'
}
}
stage("Unit and Integration Tests") {
steps {
echo 'Running JUnit tests to verify code functionality.'
// sh 'mvn test'
echo 'Executing integration tests to ensure components work together.'
// sh 'mvn verify'
}
post {
success {
mail to: "aggarwalsanchit2005@gmail.com",
subject: "Success: JUnit and Integration tests successful.",
body: "JUnit and integration tests have passed successfully. The stage is working."
}
failure {
mail to: "aggarwalsanchit2005@gmail.com",
subject: "Failure: JUnit and Integration tests failure.",
body: "JUnit and integration tests have failed. The stage is not working. Please review
the logs."
}
}
}
stage("Code Analysis") {
steps {
echo 'Performing code analysis using SonarQube'
// sh 'mvn sonar:sonar'
}
}
stage("Security Scan") {
steps {
echo 'Conducting security scan using OWASP ZAP'
// sh 'zap-cli quick-scan --self-contained --start-options "-config api.disablekey=true"
http://localhost:8080'
}
post {
success {
mail to: "aggarwalsanchit2005@gmail.com",
subject: "Success: Security scan successful.",
body: "The security scan has completed successfully. The scan is secure."
}
failure {
mail to: "aggarwalsanchit2005@gmail.com",
subject: "Failure: Security scan failed.",
body: "The security scan has failed. The scan is not secure. Please review and take
necessary actions."
}
}
}
stage("Deploy to Staging") {
steps {
echo 'Deploying to staging server (AWS EC2) at s3://staging-bucket/'
// sh 'aws deploy create-deployment --application-name my-app --deployment-group-name
staging-group --s3-location bucket=staging-bucket,key=my-app.zip'
}
}
stage("Integration Tests on Staging") {
steps {
echo 'Running integration tests in the staging environment'
// sh 'mvn verify -Dtest=IntegrationTest'
}
}
stage("Deploy to Production") {
steps {
echo 'Deploying to production server (AWS EC2)'
// sh 'aws deploy create-deployment --application-name my-app --deployment-group-name
production-group --s3-location bucket=production-bucket,key=my-app.zip'
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
