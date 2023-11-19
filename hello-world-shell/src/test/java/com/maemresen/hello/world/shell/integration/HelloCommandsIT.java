package com.maemresen.hello.world.shell.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HelloCommandsIT {

    @Autowired
    ShellTestClient client;

    @Test
    void shouldHelloWorldWithDefault() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("hello-world")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("Hello world")
                    .containsText("spring");
        });
    }

    @Test
    void shouldHelloWorldWithArgument() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("hello-world", "Emre")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("Hello world")
                    .containsText("Emre");
        });
    }

    @Test
    void shouldHelloWorldWithArgumentIncludingSpace() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("hello-world", "Emre Sen")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("Hello world")
                    .containsText("Emre Sen");
        });
    }
}
