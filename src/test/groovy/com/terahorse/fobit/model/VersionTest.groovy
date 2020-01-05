package com.terahorse.fobit.model

import com.terahorse.fobit.UnitSpec

class VersionTest extends UnitSpec {

    def "Test of getters/setters"() {
        when:
        def version = new Version("v1", "b1")

        then:
        version.version == "v1"
        version.build == "b1"
    }

    def "Test of getters/setters 2"() {
        when:
        def version = new Version()

        then:
        version.version == null
        version.build == null
    }

}
