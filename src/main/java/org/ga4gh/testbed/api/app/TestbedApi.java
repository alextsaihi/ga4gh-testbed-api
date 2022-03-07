/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.ga4gh.testbed.api.app;

import org.apache.commons.cli.Options;
import org.ga4gh.starterkit.common.util.webserver.ServerPropertySetter;
import org.ga4gh.testbed.api.config.TestbedApiServerPropertySetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.ga4gh.testbed.api")
public class TestbedApi {
    public static void main(String[] args) {
        boolean setupSuccess = setup(args);
        if (setupSuccess) {
            SpringApplication.run(TestbedApi.class, args);
        } else {
            System.out.println("Application failed at initial setup phase, this is likely an error in the YAML config file. Exiting");
        }
    }

    private static boolean setup(String[] args) {
        Options options = new TestbedApiSpringConfig().getCommandLineOptions();
        ServerPropertySetter setter = new TestbedApiServerPropertySetter();
        return setter.setServerProperties(TestbedApiYamlConfigContainer.class, args, options, "config");
    }
}
