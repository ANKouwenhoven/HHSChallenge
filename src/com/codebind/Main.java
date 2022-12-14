package com.codebind;

import com.codebind.pages.login;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] Args) {
        ContentManager contentManager = new ContentManager();
        contentManager.setPage(new login());
    }

}
