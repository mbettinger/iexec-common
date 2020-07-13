pipeline {
    
    agent any

    stages {

        stage('Test') {
            steps {
                 withCredentials([
                 string(credentialsId: 'ADDRESS_SONAR', variable: 'address_sonar'),
                 string(credentialsId: 'SONAR_COMMON_TOKEN', variable: 'common_token')]){
                    sh './gradlew clean test sonarqube -Dsonar.projectKey=iexec-common -Dsonar.host.url=$address_sonar -Dsonar.login=$common_token --no-daemon'
                 }
                 junit 'build/test-results/**/*.xml'
            }
        }

        stage('Build') {
            steps {
                withCredentials([string(credentialsId: 'JAR_SIGNING_GPG_KEY_PASSWORD', variable: 'SIGNING_PASSWORD')]){
                sh './gradlew build -Psigning.password=$SIGNING_PASSWORD --no-daemon'
                }
            }
        }

        stage('Upload Archive') {
            when {
                anyOf{
                    branch 'master'
                    branch 'develop'
                }
            }
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'ossrh-credentials',
                                  usernameVariable: 'OSSRH_USER', passwordVariable: 'OSSRH_PASSWORD']]) {
                    sh './gradlew uploadArchives -PossrhUsername=$OSSRH_USER -PossrhPassword=$OSSRH_PASSWORD --no-daemon'
                }
                archiveArtifacts artifacts: 'build/libs/*.jar'
            }
        }

        stage ("Notify iexec-core") {
            when {
                anyOf{
                    branch 'master'
                    branch 'develop'
                }
            }
            steps {
                build job: 'iexec-core/'+ env.BRANCH_NAME, propagate: true, wait: false
            }
        }

        stage ("Notify iexec-worker") {
            when {
                anyOf{
                    branch 'master'
                    branch 'develop'
                }
            }
            steps {
                build job: 'iexec-worker/'+ env.BRANCH_NAME, propagate: true, wait: false
            }
        }

    }
    
}