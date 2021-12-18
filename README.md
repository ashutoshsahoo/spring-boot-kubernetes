# Spring Boot Kubernetes

Sample project to use spring boot application in kubernetes with jkube maven plugin.

* Build application

```shell

mvn clean package

```

## Docker image with layered architecture

For docker image with layered architecture, refer [Jenkinsfile](./Jenkinsfile) and [Dockerfile](./deploy/Dockerfile)
directory.

* Open shell in project directory and run following command to build docker image and deploy to kubernetes.

```shell
docker build -f deploy/Dockerfile --build-arg VERSION=5.0.0 -t spring-boot-kubernetes:5.0.0 .
kubectl apply -f deploy/kubernetes.yml
```

* Check pod and service status

```sh

kubelet get po,svc

```

* Verify application status

```sh

curl --location --request GET 'http://localhost:31000'

```

* Delete Kubernetes deployment

```shell
kubectl delete -f deploy/kubernetes.yml
```

## Deploy application using jkube maven plugin (_Not working with java 17_)

* Application deployment

```shell

mvn -Pk8s k8s:build k8s:resource k8s:deploy

```

* Verify application

```sh

curl --location --request GET 'http://localhost:31000'

```

* UnDeploy application

```shell

mvn -Pk8s k8s:undeploy

```
