#!/bin/bash

. /var/lib/jenkins/scripts/project.envs
 
repoName=$1
deployKey=$2
baseURL=$baseURL
#apiVersion=$apiVersion
apiVersion="1.0"
username=$username
password=$password
organization=$organization
jenkinsHome=$jenkinsHome
keyName=$keyName


function deployKey(){

repoName=$1
deployKey=$2


        curl -s -X POST -u ${username}:${password} "${baseURL}/${apiVersion}/repositories/${organization}/${repoName}/deploy-keys" --data-urlencode "key=${keyFileContent}"
}


if [ $deployKey != null ]; then
	
	keyFileContent=$(cat ${jenkinsHome}/workspace/DevopsDeploySSHKey/${keyName})
	deployKey ${repoName} ${keyFileContent}

else
	if [[ -z ${jenkinsHome}/.ssh/id_rsa.pub ]]; then
		
		keyFileContent=$(cat ${jenkinsHome}/.ssh/${keyName})
		deployKey ${repoName} ${keyFileContent}
	else
		ssh-keygen -f id_rsa -t rsa -N ''
		keyFileContent=$(cat ${jenkinsHome}/.ssh/${keyName})
                deployKey ${repoName} ${keyFileContent}
	fi
fi
