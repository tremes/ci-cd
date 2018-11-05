node('cirhos_rhel7'){
        stage('Clone QE repository') {
            dir('.') {
                git branch: "${BRANCH}", url: "${REPOSITORY}"
            }
        }
        stage('Run the smoke tests'){
            dir('node-testsuite'){
                sh '''
                    oc login ${CLUSTER_URL} -u ${ADMIN_USERNAME} -p ${ADMIN_PASSWORD} --insecure-skip-tls-verify
                    export USER_TOKEN=$(oc whoami -t)
                    npm install
                    gulp smoke
                '''
            }
        }
}