package com.squarebear.web.test;

import com.squarebear.web.page.LoginPage;
import com.squarebear.web.util.Credential;
import org.fluentlenium.core.hook.wait.Wait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Wait
public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void loginShouldSucceed() {
        Credential credential = defaultCredential();
        LoginPage loginPage = goTo(LoginPage.class, credential.getBaseUrl());

        assertThat(loginPage.hasLoginForm()).isTrue();
        loginPage.fillAndSubmitForm(credential.getEmail(), credential.getPassword());
        assertThat(loginPage.isNotAtPage()).isTrue();

    }
}
