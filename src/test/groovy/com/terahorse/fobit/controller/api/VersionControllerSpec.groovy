package com.terahorse.fobit.controller.api

import com.terahorse.fobit.IntegrationSpec
import com.terahorse.fobit.model.Version
import com.terahorse.fobit.service.VersionService
import org.junit.Test

class VersionControllerSpec extends IntegrationSpec {

    @Test
    def "getVersion()"() {
        when: "read version"
        def version = readPage("/api/version", Version)

        then: "receive version"
        version.version == VersionService.NOT_AVAILABLE || version.version.matches(/^\d+.*/)
        version.build == VersionService.NOT_AVAILABLE || version.build.matches(/^\d+.*/)
    }

}
