package com.manikanta.microservices.project.UserService.Enums;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum MyFeatures implements Feature {

    @Label("New Checkout Feature")
    @EnabledByDefault
    NEW_CHECKOUT_FEATURE,

    @Label("Experimental Feature")
    EXPERIMENTAL_FEATURE
}