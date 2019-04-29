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
package com.vaadin.flow.component.notification.demo;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;

/**
 * View for {@link Notification} demo.
 */
@Route("vaadin-notification")
public class NotificationView extends DemoView {

    private static final String BUTTON_CAPTION = "Open notification";

    @Override
    public void initView() {
        createDefaultNotificaiton();
        createNotificationWithPosition();
        createNotificationUsingStaticConvenienceMethod();
        createNotificationWithComponents();
        addStyledNotificationContent();
        createThemeVariants();
    }

    private void createDefaultNotificaiton() {
        NativeButton button = new NativeButton(BUTTON_CAPTION);
        // begin-source-example
        // source-example-heading: Default Notification
        Notification notification = new Notification(
                "This notification has text content", 3000);
        button.addClickListener(event -> notification.open());
        // end-source-example
        button.setId("default-notification-button");
        notification.setId("default-notification");
        addCard("Default Notification", button);
    }

    private void createNotificationWithPosition() {
        NativeButton button = new NativeButton(BUTTON_CAPTION);
        // begin-source-example
        // source-example-heading: Notification with position
        Notification notification = new Notification(
                "This notification is located on Top-Left", 3000,
                Position.TOP_START);
        button.addClickListener(event -> notification.open());
        // end-source-example
        button.setId("position-notification-button");
        notification.setId("position-notification");
        addCard("Notification with position", button);
    }

    private void createNotificationUsingStaticConvenienceMethod() {
        // begin-source-example
        // source-example-heading: Notification using static convenience method
        Notification notification = Notification.show(
                "This is a notification created with static convenience method");
        // end-source-example
        notification.setId("static-notification");
        addCard("Notification using static convenience method", notification);
    }

    private void createNotificationWithComponents() {
        NativeButton button = new NativeButton(BUTTON_CAPTION);
        button.setId("component-notification-button");
        // begin-source-example
        // source-example-heading: Notification with components
        Label content = new Label(
                "Hello, I am a notification with components!");
        NativeButton buttonInside = new NativeButton("Bye");
        Notification notification = new Notification(content, buttonInside);
        notification.setDuration(3000);
        buttonInside.addClickListener(event -> notification.close());
        notification.setPosition(Position.MIDDLE);
        button.addClickListener(event -> notification.open());
        // end-source-example
        notification.setId("component-notification");
        content.setId("label-inside-notification");
        buttonInside.setId("button-inside-notification");
        addCard("Notification with components", button);
    }

    private void addStyledNotificationContent() {
        NativeButton button = new NativeButton(BUTTON_CAPTION);

        // begin-source-example
        // source-example-heading: Notification with styled content
        Div content = new Div();
        content.addClassName("my-style");
        content.setText("This component is styled using global styles");

        Notification notification = new Notification(content);
        notification.setDuration(3000);

        // @formatter:off
        String styles = ".my-style { "
                + "  color: red;"
                + " }";
        // @formatter:on

        /*
         * The code below register the style file dynamically. Normally you
         * use @StyleSheet annotation for the component class. This way is
         * chosen just to show the style file source code.
         */
        StreamRegistration resource = UI.getCurrent().getSession()
                .getResourceRegistry()
                .registerResource(new StreamResource("styles.css", () -> {
                    byte[] bytes = styles.getBytes(StandardCharsets.UTF_8);
                    return new ByteArrayInputStream(bytes);
                }));
        UI.getCurrent().getPage().addStyleSheet(
                "base://" + resource.getResourceUri().toString());

        button.addClickListener(event -> notification.open());
        // end-source-example

        button.setId("styled-content-notification-button");
        addCard("Notification with styled content", button);
    }

    private void createThemeVariants() {
        createDefault();
        createPrimary();
        createContrast();
        createSuccess();
        createError();
    }

    private void createDefault() {
        // begin-source-example
        // source-example-heading: Default
        Notification notification = new Notification(
                new Text("Please update your password"));

        Button notNowButton = new Button("Not now", e -> notification.close());

        Button openSettingsButton = new Button("Open settings",
                e -> notification.close());
        openSettingsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        notification.add(notNowButton, openSettingsButton);

        Button openButton = new Button("Default notification",
                e -> notification.open());

        notNowButton.getStyle().set("margin", "0 var(--lumo-space-s)");
        // end-source-example
        addCard("Theme Variants", "Default", openButton);
    }

    private void createPrimary() {
        // begin-source-example
        // source-example-heading: Primary
        Notification notification = new Notification(
                new Text("Get notified with our latest updates"));
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

        Button skipButton = new Button("Skip", e -> notification.close());

        Button subscribeButton = new Button("Subscribe",
                e -> notification.close());
        subscribeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        notification.add(skipButton, subscribeButton);

        Button openButton = new Button("Primary notification",
                e -> notification.open());
        openButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        skipButton.getStyle().set("margin", "0 var(--lumo-space-s)");
        // end-source-example
        addCard("Theme Variants", "Primary", openButton);
    }

    private void createContrast() {
        // begin-source-example
        // source-example-heading: Contrast
        Notification notification = new Notification(
                new Text("Message deleted"));
        notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);

        Button dismissButton = new Button("Dismiss", e -> notification.close());

        Button undoButton = new Button("Undo", e -> notification.close());
        undoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        notification.add(dismissButton, undoButton);

        Button openButton = new Button("Contrast notification",
                e -> notification.open());
        openButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST,
                ButtonVariant.LUMO_PRIMARY);

        dismissButton.getStyle().set("margin", "0 var(--lumo-space-s)");
        // end-source-example
        addCard("Theme Variants", "Contrast", openButton);
    }

    private void createSuccess() {
        // begin-source-example
        // source-example-heading: Success
        Notification notification = new Notification(
                new Text("New version deployed sucessfully"));
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        Button viewLogButton = new Button("View log",
                e -> notification.close());

        Button openSiteButton = new Button("Open site",
                e -> notification.close());
        openSiteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        notification.add(viewLogButton, openSiteButton);

        Button openButton = new Button("Success notification",
                e -> notification.open());
        openButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS,
                ButtonVariant.LUMO_PRIMARY);

        viewLogButton.getStyle().set("margin", "0 var(--lumo-space-s)");
        // end-source-example
        addCard("Theme Variants", "Success", openButton);
    }

    private void createError() {
        // begin-source-example
        // source-example-heading: Error
        Notification notification = new Notification(
                new Text("System error occured"));
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Button thisIsFineButton = new Button("This is fine",
                e -> notification.close());

        Button investigateButton = new Button("Investigate",
                e -> notification.close());
        investigateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        notification.add(thisIsFineButton, investigateButton);

        Button openButton = new Button("Error notification",
                e -> notification.open());
        openButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        thisIsFineButton.getStyle().set("margin", "0 var(--lumo-space-s)");
        // end-source-example
        addCard("Theme Variants", "Error", openButton);
    }

}
