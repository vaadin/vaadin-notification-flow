/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.notification.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.notification.demo.NotificationView;
import com.vaadin.flow.demo.ComponentDemoTest;
import com.vaadin.testbench.By;

/**
 * Integration tests for the {@link NotificationView}.
 */
public class NotificationIT extends ComponentDemoTest {

    private static final String DIALOG_OVERLAY_TAG = "vaadin-notification-overlay";

    @Test
    public void DefaultNotification() {
        findElement(By.id("default-notification-button")).click();
        checkNotificationIsOpen();
        assertNotificationOverlayContent("text content");
        Assert.assertEquals(1,
                findElements(By.id("default-notification")).size());
        checkNotificationIsClose();
    }

    @Test
    public void NotificationWithPosition() {
        findElement(By.id("position-notification-button")).click();
        checkNotificationIsOpen();
        assertNotificationOverlayContent("Top-Left");
        Assert.assertEquals(1,
                findElements(By.id("position-notification")).size());

        checkNotificationIsClose();

    }

    @Test
    public void NotificationWithComponent() {
        findElement(By.id("component-notification-button")).click();
        checkNotificationIsOpen();
        Assert.assertEquals(1,
                findElements(By.id("component-notification")).size());
        assertNotificationOverlayContent("Bye");

        Assert.assertEquals(1, getOverlayContent()
                .findElements(By.id("button-inside-notification")).size());
        Assert.assertEquals(1, getOverlayContent()
                .findElements(By.id("label-inside-notification")).size());
        getOverlayContent().findElement(By.id("button-inside-notification"))
                .click();
        checkNotificationIsClose();
    }

    private void assertNotificationOverlayContent(String expected) {
        String content = getOverlayContent().getText();
        Assert.assertTrue(content.contains(expected));
    }

    private WebElement getOverlayContent() {
        WebElement overlay = findElement(By.tagName(DIALOG_OVERLAY_TAG));
        return getInShadowRoot(overlay, By.id("content"));
    }

    private void checkNotificationIsClose() {
        waitUntil(driver -> Boolean.FALSE.toString()
                .equals(findElement(By.tagName(DIALOG_OVERLAY_TAG))
                        .getAttribute("opened")));
    }

    private void checkNotificationIsOpen() {
        waitUntil(driver -> Boolean.TRUE.toString()
                .equals(findElement(By.tagName(DIALOG_OVERLAY_TAG))
                        .getAttribute("opened")));
    }

    @Override
    protected String getTestPath() {
        return ("/vaadin-notification");
    }
}
