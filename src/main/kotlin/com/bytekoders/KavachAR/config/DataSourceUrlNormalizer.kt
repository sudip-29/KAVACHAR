package com.bytekoders.KavachAR.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.Ordered
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource

class DataSourceUrlNormalizer : EnvironmentPostProcessor, Ordered {

    override fun getOrder(): Int = Ordered.HIGHEST_PRECEDENCE

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        // Prefer explicit Spring property, fallback to AIVEN_URL env var used in application.properties
        val original = environment.getProperty("spring.datasource.url") ?: environment.getProperty("AIVEN_URL")
        if (original.isNullOrBlank()) return

        var normalized = original

        // If the URL starts with mysql:// (common in some Aiven/Heroku style envs), prepend jdbc:
        if (original.startsWith("mysql://", ignoreCase = true)) {
            normalized = "jdbc:$original"
        }

        // If normalization changed the value, add a high-priority property source so Spring uses it
        if (normalized != original) {
            val map = mapOf("spring.datasource.url" to normalized)
            val ps = MapPropertySource("normalizedDatasourceUrl", map)
            environment.propertySources.addFirst(ps)
        }
    }
}
