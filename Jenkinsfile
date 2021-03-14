pipeline {
    agent {
        node {
            label 'docker-slave' }
        }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build Application') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }

		stage ('Build Docker Image') {
            steps {
                sh 'docker image build -t ashutoshsahoo/spring-boot-kubernetes .'
            }
        }

// 		stage ('Openshift Deploy') {
//             steps {
//                 sh 'mvn -DskipTests fabric8:deploy'
//             }
//         }
    }
}
