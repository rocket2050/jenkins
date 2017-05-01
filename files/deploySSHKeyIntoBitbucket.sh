#!/bin/bash

repoName=$1
deployKey=$2
baseURL="https://api.bitbucket.org/"
apiVersion="1.0"
username="username"
password="password"
organization="yourorgname"
jenkinsHome="/var/lib/jenkins"
keyName="id_rsa.pub"


if [ $deployKey != null ]; then
	
	keyFileContent=$(cat ${jenkinsHome}/workspace/deployKey/${keyName})
	deployKey ${repoName} ${keyFileContent}

elif
	if [[ -z ${jenkinsHome}/.ssh/id_rsa.pub ]]; then
		
		keyFileContent=$(cat ${jenkinsHome}/.ssh/${keyName})
		deployKey ${repoName} ${keyFileContent}
	elif
		ssh-keygen -f id_rsa -t rsa -N ''
		keyFileContent=$(cat ${jenkinsHome}/.ssh/${keyName})
                deployKey ${repoName} ${keyFileContent}
fi


deployKey(){

repoName=$1
deployKey=$2

	curl -s -X POST -u ${username}:${password} "${baseURL}/${apiVersion}/repositories/${organization}/${repoName}/deploy-keys" --data-urlencode "key=${keyFileContent}"
}
