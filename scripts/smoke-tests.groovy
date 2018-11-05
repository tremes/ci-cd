node('cirhos_rhel7'){
        stage('Clone QE repository') {
            dir('.') {
                git branch: "${BRANCH}", url: "${REPOSITORY}"
            }
        }
        stage('Run the smoke tests'){
            dir('node-testsuite'){
                sh '''
                    npm install
                    gulp smoke
                '''
            }
        }
}