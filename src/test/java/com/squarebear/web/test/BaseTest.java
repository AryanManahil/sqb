package com.squarebear.web.test;


import com.squarebear.web.util.*;
import org.apache.commons.lang3.StringUtils;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.fluentlenium.adapter.testng.FluentTestNg;


import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

abstract class BaseTest extends FluentTestNg {

    private static final String KEY_CREDENTIALS_FILE = "external.credentials.file";
    private static final String DEFAULT_CREDENTIALS_FILE = "credentials.properties";
    private static final String KEY_SUFFIX_BASE_URL = ".baseUrl";
    private static final String KEY_SUFFIX_EMAIL = ".email";
    private static final String KEY_SUFFIX_PASSWORD = ".password";

    private static final String KEY_SUFFIX_SECRET = ".secret";
    private static final String DEFAULT_CREDENTIAL = "default";
    private static final String KEY_SUFFIX_ALIAS = ".alias";

    protected Credential loadCredential(String name) throws CredentialException {
        CredentialStore store = CredentialStore.instance();
        if (store.isNotPopulated()) {
            populateCredentialStore();
        }
        if (store.has(name + KEY_SUFFIX_ALIAS)) {
            String alias = store.get(name + KEY_SUFFIX_ALIAS);
            if (StringUtils.isNotBlank(alias)) {
                return loadCredential(alias);
            }
        }

        String baseUrl = store.get(name + KEY_SUFFIX_BASE_URL);
        String email = store.get(name + KEY_SUFFIX_EMAIL);
        String password = store.get(name + KEY_SUFFIX_PASSWORD);
        String secret = store.get(name + KEY_SUFFIX_SECRET);

        if (StringUtils.isNotBlank(baseUrl)) {
            return new Credential(baseUrl, email, password, secret);
        }
        throw new CredentialException("Credential with name: '" + name + "' not found");


    }

    /**
     * utility method to load default credential
     *
     * @return credential credential loaded using name "default"
     */

    protected Credential defaultCredential() {
        return loadCredential(DEFAULT_CREDENTIAL);
    }

    private void populateCredentialStore() {
        try {
            Properties properties = new Properties();
            properties.load(credentialsInputStream());
            CredentialStore store = CredentialStore.instance();
            properties.forEach((key, value) -> store.put(key.toString(), value.toString()));
            store.setPopulated(true);
        } catch (IOException e) {
            throw new CredentialException("Unable to load credentials from file", e);
        }

    }

    private InputStream credentialsInputStream() throws FileNotFoundException, CredentialException {
        String externalCredentialsPath = System.getProperty(KEY_CREDENTIALS_FILE);
        if (StringUtils.isNotBlank(externalCredentialsPath)) {
            File externalCredentialsFile = new File(externalCredentialsPath);
            if (externalCredentialsFile.exists()) {
                return new FileInputStream(externalCredentialsFile);
            } else {
                throw new CredentialException("Invalid credentials file path");
            }
        } else {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_CREDENTIALS_FILE);
        }
    }

    public <P extends FluentPage> P goTo(P page, String baseUrl) {
        if (StringUtils.isNotBlank(page.getUrl())) {
            super.setBaseUrl(baseUrl);
        }
        return goTo(page);
    }

    public <P extends FluentPage> P goTo(Class<P> pageClass, String baseUrl) {
        return goTo(newInstance(pageClass), baseUrl);
    }

    @Override public Capabilities getCapabilities() {
        return SupportedWebDriver.find(getWebDriver())
                .map(driver -> driver.getCapabilities(getTargetDevice()))
                .orElseGet(super::getCapabilities);
    }

    @Override
    public WebDriver newWebDriver() {
        WebDriver webDriver = super.newWebDriver();
        boolean shouldResizeWindow = SupportedWebDriver.find(getWebDriver())
                .map(SupportedWebDriver::isWindowResizeRequired)
                .orElse(false);
        if (shouldResizeWindow) {
            webDriver.manage().window().setSize(getTargetDevice().getScreenSize());
        }
        return webDriver;
    }

    private Device targetDeviceFromAnnotation;
    private Device getTargetDevice() {
        if (targetDeviceFromAnnotation != null) {
            return targetDeviceFromAnnotation;
        }
        return Device.DESKTOP;
    }


}



