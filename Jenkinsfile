pipeline {
    agent {
        node {
            label 'docker-slave'
        }
    }

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        ARTIFACT_ID = readMavenPom().artifactId
        ARTIFACT_VERSION = readMavenPom().version
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
                sh """
                echo IMAGE: ${ARTIFACT_ID}
                echo VERSION: ${ARTIFACT_VERSION}
                mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
                docker image build --build-arg IMAGE_VERSION=${ARTIFACT_VERSION} -t ${ARTIFACT_ID}:${ARTIFACT_VERSION} .
                """
            }
        }

        stage ('Kubernetes Deploy') {
            steps {
                sh 'kubectl apply -f kubernetes.yml'
            }
        }
    }
}
