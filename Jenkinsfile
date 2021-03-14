pipeline {
    agent {
        node {
            label 'docker-slave'
        }
    }
    stages {

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
                sh 'docker image build -t spring-boot-kubernetes .'
            }
        }

		stage ('Build with fabric8 plugin') {
            steps {
               sh 'mvn fabric8:resource fabric8:build'
            }
        }

        stage ('Kubernetes Deploy') {
            steps {
                sh 'mvn -DskipTests fabric8:deploy'
            }
        }
    }
}
