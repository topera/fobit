package com.terahorse.fobit.controller.pages

import com.terahorse.fobit.IntegrationSpec
import org.junit.Test

class MainControllerSpec extends IntegrationSpec {

    @Test
    def "main page check"() {
        when:
        def html = readPage("/")

        then:
        html.contains('?q=')
    }
}
