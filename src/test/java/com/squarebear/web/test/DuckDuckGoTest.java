package com.squarebear.web.test;


import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.hook.wait.Wait;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class DuckDuckGoTest extends FluentTestNg {
    @Test
    public void titleOfDuckDuckGoShouldContainSearchQueryName() {
        goTo("https://duckduckgo.com");
        $("#search_form_input_homepage").fill().with("FluentLenium");
        $("#search_button_homepage").submit();
        assertThat(window().title()).contains("FluentLenium");
    }
}
