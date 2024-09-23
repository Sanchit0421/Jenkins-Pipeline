post {
    success {
        emailext(
            to: 'aggarwalsanchit2005@gmail.com',
            subject: "Success: JUnit and Integration tests successful.",
            body: """
                JUnit and integration tests have passed successfully.
                The stage is working as expected.
            """
        )
    }
    failure {
        emailext(
            to: 'aggarwalsanchit2005@gmail.com',
            subject: "Failure: JUnit and Integration tests failure.",
            body: """
                JUnit and integration tests have failed.
                Please review the logs and take necessary action.
            """
        )
    }
}

