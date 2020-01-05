package com.terahorse.gradle

@SuppressWarnings("GroovyUnusedDeclaration")
class RemoteExec extends SimpleExec {

    def user
    def host
    def keyPath

    void runRemote(String command) {
        super.run(createSSHCommand(command))
    }

    void runScp(String from, String to) {
        def scp = "scp -o 'StrictHostKeyChecking no' -i $keyPath $from ${user}@${host}:${to}"
        super.run(scp)
    }

    def createSSHCommand(def command){
        return "ssh -o 'StrictHostKeyChecking no' -i $keyPath $user@$host -C \"$command\""
    }


}