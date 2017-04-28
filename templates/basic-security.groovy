#!groovy
import hudson.security.*
import jenkins.model.*

def instance = Jenkins.getInstance()

println "--> Checking if security has been set already"

if (!instance.isUseSecurity()) {
    println "--> creating local user 'admin'"

    def hudsonRealm = new HudsonPrivateSecurityRealm(false)
    hudsonRealm.createAccount('{{ jenkins_admin_username }}', '{{ jenkins_admin_password }}')
    instance.setSecurityRealm(hudsonRealm)

    def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
    instance.setAuthorizationStrategy(strategy)
    instance.save()
}

hudson = new XmlSlurper().parse("/var/lib/jenkins/config.xml")
def anonymousAccess = hudson.authorizationStrategy.denyAnonymousReadAccess

anonymousAccess.replaceBody 'true'
def configContent = groovy.xml.XmlUtil.serialize( hudson )
new File("/var/lib/jenkins/","config.xml").withWriter('utf-8') { 
	         writer -> writer.write (configContent)
}

