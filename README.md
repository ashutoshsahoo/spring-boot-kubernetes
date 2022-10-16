# Spring Boot Kubernetes

Sample project to use spring boot application in kubernetes with jkube maven plugin.

* Build application

```shell
mvn clean package
```

## Docker image with layered architecture

For docker image with layered architecture, refer [Jenkinsfile](./Jenkinsfile) and [Dockerfile](./deploy/Dockerfile)
directory.

* Open shell in project directory and run following command to build docker image.

```shell
docker build -f deploy/Dockerfile --build-arg VERSION=6.2.0 -t ashutoshsahoo/spring-boot-kubernetes:6.2.0 .
```

## Kubernetes deployment

* Deploy to kubernetes

```shell
kubectl apply -f deploy/kubernetes.yml
```

* Check pod and service status

```sh
kubelet get po,svc
```

* Verify application status

```sh
curl --location --request GET 'http://localhost:31000/api/v5/hello'
```

* Delete Kubernetes deployment

```shell
kubectl delete -f deploy/kubernetes.yml
```

## Kubernetes Istio service mesh deployment

This is a demonstration of running two different versions of same application and redirecting traffic based on path
prefix.

Please follow the instructions [here](https://istio.io/latest/docs/setup/getting-started/) to configure istio inside
docker desktop.

* Deploy app

```shell
kubectl apply -f deploy/istio
```

* Check application status

```sh
kubelet get all
```

* Verify application status

```sh
curl --location --request GET 'http://localhost/api/v5/hello'
```

* Run following command to put some traffic into system.

```shell
for i in $(seq 1 100); do curl -s -o /dev/null "http://localhost/api/v5/hello"; done
for i in $(seq 1 100); do curl -s -o /dev/null "http://localhost/api/v6/hello"; done
```

Open [kiali dashboard](http://localhost:20001/kiali) to view application metrics.

* Delete deployment

```shell
kubectl delete -f deploy/istio
```

* Clean up istio configuration

Refer the documentation [here](https://istio.io/latest/docs/setup/getting-started/#uninstall) to clean up.

## Deploy application using jkube maven plugin

* Application deployment

```shell
mvn -Pk8s k8s:build k8s:resource k8s:deploy
```

* Verify application

```sh
kubectl exec -it deployments.apps/spring-boot-kubernetes -- bash
curl --location --request GET 'http://localhost:8080/api/v5/hello'
```

* UnDeploy application

```shell
mvn -Pk8s k8s:undeploy
```
