pipeline{
    agent any
    stages{
        stage("Build"){
            steps{
                echo "Building,,,"
            }
            post{
                success{
                    mail to: "aggarwalsanchit2005@gmail.com",
                    subject: "Build status email",
                    body: "Build was successful!"
                }
            }
            }
            stage("Test"){
                steps{
                    echo "testing..."
                }
            }
            stage("Deploy"){
                steps{
                    echo"Deploying..."
                }
            }
                }
            }