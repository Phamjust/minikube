pipeline {
	agent any
	environment{
		registry='revnkog/project2'
        dockerImage=''
        dockerHubCredentials='DockerHub'
	}
	stages{
		stage("Maven build"){
			steps{
				sh '/usr/bin/mvn clean package'
			}
		}
		stage("Docker build"){
			steps{
				script{
					dockerImage = docker.build "$registry"
				}
			}
		}
		stage("Pushing to DockerHub Registry"){
            steps{
                script{
                    docker.withRegistry('',dockerHubCredentials){
                        dockerImage.push("$currentBuild.number")
                        dockerImage.push("latest")
                    }
                }
            }
        }
        stage("Deploying to Kubernetes production environment"){
			steps{
				script{
				    sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'  
                    sh 'chmod u+x ./kubectl'
					sh 'echo $registry'
					// sh './kubectl delete -f yamlFiles_for_Deployment/bank-service-deployment.yml -n pancake-controller-space'
                    // sh './kubectl apply -f yamlFiles_for_Deployment/bank-service-deployment.yml -n pancake-controller-space'
					sh './kubectl set image -n pancake-controller-space deployment/pancake-controller-bank-app pancake-bank-deployment=$registry:latest'

				}
			}
		}
	}
}