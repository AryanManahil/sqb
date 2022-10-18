package com.squarebear.web.page;

public class NewprojectPage extends BasePage {
    public static final String PAGE_ID = "newproject-page";

    private static final String FORM_ID = "newproject-form";

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

    public NewprojectPage fillAndSubmit(String project) {
        await().untilPage().isLoaded();

        el("input[type='email']").fill().with(project);
        el("button.MuiButtonBase-root[type='submit']").waitAndClick();

        if (!hasAlertError()) {
            await().until(this::isNotAtPage);
        }
        return this;
    }
}
