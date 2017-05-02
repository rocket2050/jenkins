import groovy.json.JsonOutput

def config = new ConfigSlurper().parse(new File('/var/lib/jenkins/scripts/project.envs').toURL())

//config.each { key, value -> println "$key=$value" }

//println config.baseURL.value

String baseUrl =  "${config.baseURL.value}"
String version = "${config.apiVersion.value}"
String username =  "${config.username.value}"
String password = "${config.password.value}"
String organization = "${config.organization.value}"


//String repository = "test1"
// put it all together
String branchesUrl = [baseUrl, version, "repositories", organization, REPOSITORY , "refs", "branches"  ].join("/")

// Create authorization header using Base64 encoding
String userpass = username + ":" + password;
String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

// Create URL
URL url = branchesUrl.toURL()

// Open connection
URLConnection connection = url.openConnection()

// Set authorization header
connection.setRequestProperty ("Authorization", basicAuth)

// Open input stream
InputStream inputStream = connection.getInputStream()

// Get JSON output
//def branchesJson = new groovy.json.JsonSlurper().parseText(inputStream.text)

def branchesJson = JsonOutput.prettyPrint(inputStream.text)

def branchJson = new groovy.json.JsonSlurper().parseText(branchesJson)

def slug = branchJson.values.name

slug.each {
    println "${it}"
}

// Close the stream
//inputStream.close()
