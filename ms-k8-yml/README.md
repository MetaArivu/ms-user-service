kubectl create -f deployment.yml
kubectl create -f service.yml

kubectl delete -f service.yml
kubectl delete -f deployment.yml


kubectl logs user-service-pod --namespace shoppingportal