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
package com.vaadin.flow.component.notification;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.internal.HtmlUtils;

/**
 * Server-side component for the <code>vaadin-notification</code> element.
 *
 * @author Vaadin Ltd
 */
@HtmlImport("frontend://flow-component-renderer.html")
public class Notification
        extends GeneratedVaadinNotification<Notification>
        implements HasComponents {

    private Element container = new Element("div", false);
    private final Element templateElement = new Element("template");
    /**
     * Enumeration of all available positions for Vertical Alignment
     */
    public enum VerticalAlign {
        TOP_STRETCH, TOP, MIDDLE, BOTTOM, BOTTOM_STRETCH
    }

    /**
     * Enumeration of all available positions for Horizontal Alignment
     */
    public enum HorizontalAlign {
        START, CENTER, END
    }

    /**
     * Default constructor. Create an empty notification with component support
     * and non-auto-closing
     * <p>
     * Note: To mix text and child components in notification that also supports
     * child components, use the {@link Text} component for the textual parts.
     */
    public Notification() {
        getElement().appendChild(templateElement);
        getElement().appendVirtualChild(container);

        setAlignment(VerticalAlign.BOTTOM, HorizontalAlign.START);
        setDuration(0);
    }

    /**
     * Creates a Notification with the given String rendered as its HTML text.
     * The default duration for the notification is 4000 milliseconds
     * 
     * @param text
     *            the text of the Notification
     */
    public Notification(String text) {
        this(text, 4000, VerticalAlign.BOTTOM,
                HorizontalAlign.START);
    }

    /**
     * Creates a Notification with given String rendered as its HTML text and
     * given Integer rendered as its duration.
     * <p>
     * Set to {@code 0} or a negative number to disable the notification
     * auto-closing.
     * 
     * @param text
     *            the text of the Notification
     * @param duration
     *            the duration in milliseconds to show the notification
     */
    public Notification(String text, int duration) {
        this(text, duration, VerticalAlign.BOTTOM,
                HorizontalAlign.START);
    }

    /**
     * Creates a Notification with given text String, duration, vertical and
     * horizontal Alignment.
     * <P>
     * Set to {@code 0} or a negative number to disable the notification
     * auto-closing.
     * <P>
     * Horizontal alignment is skipped in case verticalAlign is set to
     * {@code top-stretch|middle|bottom-stretch}
     * 
     * @param text
     *            the text of the Notification
     * @param duration
     *            the duration in milliseconds to show the notification
     * @param vertical
     *            the vertical alignment of the notification. Valid values are
     *            {@code top-stretch|top|middle|bottom|bottom-stretch}
     * @param horizontal
     *            the horizontal alignment of the notification.Valid values are
     *            {@code start|center|end}
     */
    public Notification(String text, int duration, VerticalAlign vertical,
            HorizontalAlign horizontal) {
        getElement().appendChild(templateElement);
        getElement().appendVirtualChild(container);
        setText(text);
        setDuration((double) duration);
        setAlignment(vertical, horizontal);
    }

    /**
     * Creates a notification with given components inside.
     * <p>
     * Note: To mix text and child components in a component that also supports
     * child components, use the {@link Text} component for the textual parts.
     * 
     * @param components
     *            the components inside the notification
     * @see #add(Component...)
     */
    public Notification(Component... components) {
        this();
        add(components);
    }

    /**
     * Set the text of the notification with given String
     * <p>
     * NOTE: When mixing this method with {@link #Notification()} and
     * {@link #Notification(Component...)}. Method will remove all the
     * components from the notification.
     * 
     * @param text
     */
    public void setText(String text) {
        removeAll();
        templateElement.setProperty("innerHTML", HtmlUtils.escape(text));
    }

    /**
     * Set the vertical Alignment of the notification.
     * 
     * @param vertical
     *            the vertical alignment. Valid enumerate values are
     *            {@code top-stretch|top|middle|bottom|bottom-stretch}
     */
    public void setVerticalAlign(VerticalAlign vertical) {
        this.setVerticalAlign(
                vertical.toString().toLowerCase().replace('_', '-'));
    }

    /**
     * Set the horizontal Alignment of the notification.
     * <P>
     * Horizontal alignment is skipped in case verticalAlign is set to
     * {@code top-stretch|middle|bottom-stretch}
     * 
     * @param horizontal
     *            the horizontal alignment. Valid values are
     *            {@code start|center|end}
     */
    public void setHorizontalAlign(HorizontalAlign horizontal) {
        this.setHorizontalAlign(horizontal.toString().toLowerCase());
    }

    /**
     * Set vertical and Alignment of the notification.
     * <P>
     * Horizontal alignment is skipped in case verticalAlign is set to
     * {@code top-stretch|middle|bottom-stretch}
     * 
     * @param vertical
     *            the vertical alignment. Valid enumerate values are
     *            {@code top-stretch|top|middle|bottom|bottom-stretch}
     * @param horizontal
     *            the horizontal alignment. Valid values are
     *            {@code start|center|end}
     */
    public void setAlignment(VerticalAlign vertical,
            HorizontalAlign horizontal) {
        setVerticalAlign(vertical);
        setHorizontalAlign(horizontal);
    }

    /**
     * Opens the notification.
     */
    public void open() {
        setOpened(true);
    }

    /**
     * Closes the notification.
     */
    public void close() {
        setOpened(false);
    }

    /**
     * Adds the given components into this notification.
     * <p>
     * The elements in the DOM will not be children of the
     * {@code <vaadin-notification>} element, but will be inserted into an
     * overlay that is attached into the {@code <body>}.
     * <p>
     * NOTE: When mixing this method with {@link #Notification(String)},
     * {@link #Notification(String, int)} and
     * {@link #Notification(String, int, VerticalAlign, HorizontalAlign)},
     * method will remove the text content.
     *
     * @param components
     *            the components to add
     */
    @Override
    public void add(Component... components) {
        templateElement.setProperty("innerHTML", null);
        assert components != null;
        for (Component component : components) {
            assert component != null;
            container.appendChild(component.getElement());
        }
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, () -> attachComponentTemplate(ui)));
    }

    /**
     * Remove the given components from this notification.
     * 
     * @param components
     *            the components to remove
     */
    @Override
    public void remove(Component... components) {
        for (Component component : components) {
            assert component != null;
            if (container.equals(component.getElement().getParent())) {
                container.removeChild(component.getElement());
            } else {
                throw new IllegalArgumentException("The given component ("
                        + component + ") is not a child of this component");
            }
        }
    }

    /**
     * Remove all the components from this notification.
     */
    @Override
    public void removeAll() {
        container.removeAllChildren();
    }

    private void attachComponentTemplate(UI ui) {
        String appId = ui.getInternals().getAppId();
        int nodeId = container.getNode().getId();
        String template = "<flow-component-renderer appid=" + appId
                + " nodeid=" + nodeId
                + "></flow-component-renderer>";
        templateElement.setProperty("innerHTML", template);
    }
}
