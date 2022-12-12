package com.codebind;

import javax.swing.*;
import java.util.EventListener;

public interface PageInterface extends EventListener {

    JPanel getPanel();
    void setContentManager(ContentManager contentManager);

    void afterSetup();
}
