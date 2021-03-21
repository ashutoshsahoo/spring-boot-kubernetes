pipeline {
    agent { node { label 'docker-agent' } }
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    parameters {
        // booleanParam, choice, file, text, password, run, or string
        booleanParam(defaultValue: false, description: 'Do you want to deploy the application?', name: 'deployment')
        //string(defaultValue: "TEST", description: 'What environment?', name: 'stringExample')
        //text(defaultValue: "This is a multiline\n text", description: "Multiline Text", name: "textExample")
        //choice(choices: 'US-EAST-1\nUS-WEST-2', description: 'What AWS region?', name: 'choiceExample')
        //choice(choices: ['greeting' , 'silence'],description: '', name: 'REQUESTED_ACTION')
        //password(defaultValue: "Password", description: "Password Parameter", name: "passwordExample")
    }

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into environment variables
        ARTIFACT_ID = readMavenPom().getArtifactId()
        ARTIFACT_VERSION = readMavenPom().getVersion()
        DEPLOYMENT= "${params.deployment}"
    }

    stages {
        stage ('Build Application') {
            steps {
                sh 'mvn clean install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    sh 'echo Build failed, Sending notification....'
                    // logic to send notification
                }
            }
        }

        stage ('Build Docker Image') {
            steps {
                sh """
                echo IMAGE: ${ARTIFACT_ID}
                echo VERSION: ${ARTIFACT_VERSION}
                docker build -f deploy/Dockerfile \
                --build-arg JAR_FILE=target/${ARTIFACT_ID}-${ARTIFACT_VERSION}.jar \
                --build-arg IMAGE_VERSION=${ARTIFACT_VERSION} \
                -t ${ARTIFACT_ID}:${ARTIFACT_VERSION} .
                """
            }
        }

        stage ('Kubernetes Deploy') {
        when { expression { env.DEPLOYMENT.toBoolean() }}
            steps {
                sh 'kubectl --kubeconfig=/etc/mk8s/kube.config  apply -f deploy/kubernetes.yml'
            }
        }
    }
}
