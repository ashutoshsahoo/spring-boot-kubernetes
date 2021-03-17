# Spring Boot Kubernetes

Sample project to use spring boot application in kubernetes with fabric8 maven plugin.



* Build application
```shell

mvn clean package

```

* Deploy application using jkube maven plugin
```shell

mvn -Pk8s k8s:build k8s:resource k8s:deploy

```

* Verify Application

```sh

curl --location --request GET 'http://localhost:31000'

```


* UnDeploy application using jkube maven plugin
```shell

mvn -Pk8s k8s:undeploy

```

## Docker image with layered architecture

For docker image with layered architecture, refer [Jenkinsfile](./Jenkinsfile) and [Dockerfile](./deploy/Dockerfile) directory.