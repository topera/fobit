package com.terahorse.gradle

@SuppressWarnings("GroovyUnusedDeclaration")
class AWSExec extends RemoteExec {

    void runRemote(String command) {
        setServerInfo()
        super.runRemote(command)
    }

    void runScp(String from, String to) {
        setServerInfo()
        super.runScp(from, to)
    }

    private void setServerInfo() {
        user = System.getenv('AWS_GRADLE_USER')
        host = System.getenv('AWS_GRADLE_HOST')
        keyPath = System.getenv('AWS_GRADLE_KEY_PATH')
    }


}