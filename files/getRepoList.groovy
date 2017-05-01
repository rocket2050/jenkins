import groovy.json.JsonOutput  

String baseUrl = "https://api.bitbucket.org"
String apiversion = "2.0"
String username = "username"
String password = "password"
String organization = "yourorgname"

// put it all together
String branchesUrl = [baseUrl, version, "repositories", organization  ].join("/")
//String branchesUrl = [ firstbranchesUrl, "q='project.key=\"TES\"'"  ].join("?")


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

def reposJson = JsonOutput.prettyPrint(inputStream.text)

def repoJson = new groovy.json.JsonSlurper().parseText(reposJson)


def slug = repoJson.values.slug

slug.each {
    println "${it}"
}

// Close the stream
inputStream.close()
