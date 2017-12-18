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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.HorizontalAlign;
import com.vaadin.flow.component.notification.Notification.VerticalAlign;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link Notification} demo.
 */
@Route("vaadin-notification")
@HtmlImport("bower_components/vaadin-valo-theme/vaadin-button.html")
public class NotificationView extends DemoView {

    private static final String BUTTON_CAPTION = "Open notification";

    @Override
    public void initView() {
        createDefaultNotificaiton();
        createNotificationWithPosition();
    }

    private void createDefaultNotificaiton() {
        Button button = new Button(BUTTON_CAPTION);
        // begin-source-example
        // source-example-heading: Default Notification
        Notification notification = new Notification(
                "<h3>Hello World!</h3>"
                        + "This notification has HTML content",
                4);
        // end-source-example
        button.addClickListener(event -> notification.open());
        addCard("Default Notification", notification, button);
    }

    private void createNotificationWithPosition() {
        Button button = new Button(BUTTON_CAPTION);
        // begin-source-example
        // source-example-heading: Notification with position
        Notification notification = new Notification(
                "<h3>Hello World!</h3>"
                        + "This notification has positioning information",
                4000, VerticalAlign.MIDDLE, HorizontalAlign.START);
        // end-source-example
        button.addClickListener(event -> notification.open());
        addCard("Notification with position", notification, button);
    }
}
