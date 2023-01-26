package com.codebind;

import com.codebind.pages.Login;

public class Main {

    public static ContentManager contentManager;

    public static void main(String[] Args) {
        contentManager = new ContentManager();
        contentManager.setPage(new Login());
    }



}
