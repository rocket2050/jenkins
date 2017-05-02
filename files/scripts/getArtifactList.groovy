import static groovy.io.FileType.FILES

def config = new ConfigSlurper().parse(new File('/var/lib/jenkins/scripts/project.envs').toURL())

String baseUrl =  "${config.baseURL.value}"


String jenkinshome = "${config.jenkinsHome.value}"
String artifactbasedir = "${config.artifactBaseDir.value}"


def files = []

new File("${jenkinshome}/${artifactbasedir}/$REPOSITORY/$BRANCH").eachFileRecurse(FILES) {artifact->   
    files <<   artifact.getName() //+  BRANCH 
    }
files.each {
    println "${it}"
}
