import static groovy.io.FileType.FILES

String jenkinshome = "/var/lib/jenkins"
String artifactbasedir = "BUILDS"


def files = []

new File("${jenkinshome}/${artifactbasedir}/$REPOSITORY/$BRANCH").eachFileRecurse(FILES) {artifact->   
    files <<   artifact.getName() //+  BRANCH 
    }
files.each {
    println "${it}"
}
