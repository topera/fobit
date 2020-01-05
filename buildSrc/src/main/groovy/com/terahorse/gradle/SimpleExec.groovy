package com.terahorse.gradle

import org.gradle.api.tasks.Exec

@SuppressWarnings("GroovyUnusedDeclaration")
class SimpleExec extends Exec {

    private List<String> commands = []

    void run(String command) {
        commands.add("echo '$command'")
        commands.add(command)

        executable 'sh'
        args = ["-c", commands.join(";")]
    }

}