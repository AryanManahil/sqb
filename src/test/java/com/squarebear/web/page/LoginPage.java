package com.squarebear.web.page;

public class LoginPage extends BasePage {

    public static final String PAGE_ID = "login-page";

    private static final String FORM_ID = "login-form";

    @Override
    public String getPageId() {
        return PAGE_ID;
    }

    @Override
    public String getUrl() {
        return "http://3.26.192.52:8080/login";
    }

    public boolean hasLoginForm() {
        return hasForm(FORM_ID);
    }

    public LoginPage fillAndSubmitForm(String email, String password) {
        await().untilPage().isLoaded();

        el("input[type='email']").fill().with(email);
        el("input[type='password']").fill().with(password);
        el("button.MuiButtonBase-root[type='submit']").waitAndClick();

        if (!hasAlertError()) {
            await().until(this::isNotAtPage);
        }
        return this;
    }
}
