package com.codebind;

import com.codebind.pages.Login;

public class Main {

    public static void main(String[] Args) {
        ContentManager contentManager = new ContentManager();
        contentManager.setPage(new Login());
    }



}
