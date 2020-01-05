package com.terahorse.fobit.util

import com.terahorse.fobit.IntegrationSpec
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ResourceUtilSpec extends IntegrationSpec {

    @Autowired
    ResourceUtil resourceUtil

    @Test
    def "reading version"() {
        when:
        def version = resourceUtil.readFile("version.txt")

        then:
        version == null || version.matches(/^\d+.*/) // null locally, and valid in CI server
    }

}
