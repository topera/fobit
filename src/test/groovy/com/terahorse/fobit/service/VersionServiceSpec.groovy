package com.terahorse.fobit.service

import com.terahorse.fobit.IntegrationSpec
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class VersionServiceSpec extends IntegrationSpec {

    @Autowired
    VersionService versionService

    @Test
    def "get version"() {
        when:
        def version = versionService.version

        then:
        version.version == VersionService.NOT_AVAILABLE || version.version.matches(/^\d+.*/)
        version.build == VersionService.NOT_AVAILABLE || version.build.matches(/^\d+.*/)
    }

}
