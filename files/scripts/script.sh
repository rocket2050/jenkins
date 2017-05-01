baseURL="$1"
apiVersion="$2"
username="$3"
password="$4"
organization="$5"
jenkinsHome="$6"
keyName="$7"


sed -e "s/^String baseURL.*/String baseURL = \"$baseURL\"/" test.groovy  | sed -e "s/^String apiVersion.*/String apiVersion = \"$apiVersion\"/" test.groovy |sed -e "s/^String username.*/String username = \"$username\"/" test.groovy | sed -e "s/^String password.*/String password = \"$password\"/" test.groovy | sed -e "s/^String organization.*/String organization = \"$organization\"/" test.groovy | sed -e "s/^String jenkinsHome.*/String jenkinsHome = \"$jenkinsHome\"/" test.groovy | sed -e "s/^String keyName.*/String keyName = \"$keyName\"/" test.groovy
