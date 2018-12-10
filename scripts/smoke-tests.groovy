node('cirhos_rhel7'){
        stage('Clone QE repository') {
            dir('.') {
                git branch: "${BRANCH}", url: "${REPOSITORY}"
            }
        }
        stage('Run the smoke tests'){
            dir('node-testsuite'){
                try {
                    sh '''
                        npm install
                        gulp smoke
                    '''
                } catch(exp) {
                    println exp
                    currentBuild.result = 'UNSTABLE'
                }
            }
        }
}