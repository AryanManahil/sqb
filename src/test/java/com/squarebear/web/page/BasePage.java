package com.squarebear.web.page;

import org.fluentlenium.core.FluentPage;


import java.util.Objects;

import static org.fluentlenium.core.filter.FilterConstructor.with;

public abstract class BasePage extends FluentPage {
    private static final String ELEMENT_ID = "data-element-id";
    private static final String TAG_BODY = "body";
    private static final String TAG_FORM = "form";
    private static final String CLASS_ALERT = ".alert";
    private static final String CLASS_ALERT_SUCCESS = ".alert-success";
    private static final String CLASS_ALERT_WARNING = ".alert-warning";
    private static final String CLASS_ALERT_DANGER = ".alert-danger";

    public abstract String getPageId();

    public String currentPageId() {
        return el(TAG_BODY).attribute(ELEMENT_ID);
    }

    public boolean isPage(String pageId) {
        return Objects.equals(pageId, currentPageId());
    }

    public boolean isAtPage() {
        return isPage(getPageId());
    }

    public boolean isNotAtPage() {
        return !isAtPage();
    }

    public boolean hasAlert() {
        return find(CLASS_ALERT).count() > 0;
    }

    public boolean hasAlertWarning() {
        return find(CLASS_ALERT_WARNING).count() > 0;
    }

    public boolean hasAlertError() {
        return find(CLASS_ALERT_DANGER).count() > 0;
    }

    public boolean hasForm(String formId) {
        return find(TAG_FORM, with(ELEMENT_ID).equalTo(formId)).count() > 0;
    }
}
