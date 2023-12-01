package edu.project1;

import java.util.List;

public class AsciiPictures {
    public static final int MAX_HEALTH_POINTS = 5;
    public static final String TITLE = """
        ██╗░░██╗░█████╗░███╗░░██╗░██████╗░███╗░░░███╗░█████╗░███╗░░██╗
        ██║░░██║██╔══██╗████╗░██║██╔════╝░████╗░████║██╔══██╗████╗░██║
        ███████║███████║██╔██╗██║██║░░██╗░██╔████╔██║███████║██╔██╗██║
        ██╔══██║██╔══██║██║╚████║██║░░╚██╗██║╚██╔╝██║██╔══██║██║╚████║
        ██║░░██║██║░░██║██║░╚███║╚██████╔╝██║░╚═╝░██║██║░░██║██║░╚███║
        ╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝
        """;

    public static final String HANGED_ART = """
               (/)
               (/)
                (/)
               (/)
                (/)
               (/)
               (/))
              (/)(/)
             (/)'`(/)
            (/)    (/)
            (/)    (/)
            (/)    (/)
            (/)    (/)
             (/)  (/)
              (/)(/)
            Hanged...
        """;

    private static final List<String> PICTURES_BY_HEALTH_POINTS = List.of(
        """
                  _______
                 |/      |
                 |      (_)
                 |      \\|/
                 |       |
                 |      / \\
                 |
                _|___
            """
        ,
        """
                  _______
                 |/      |
                 |      (_)
                 |      \\|/
                 |       |
                 |      /
                 |
                _|___
            """
        ,
        """
                  _______
                 |/      |
                 |      (_)
                 |      \\|/
                 |
                 |
                 |
                _|___
            """
        ,
        """
                  _______
                 |/      |
                 |      (_)
                 |      \\|
                 |
                 |
                 |
                _|___
            """
        ,
        """
                  _______
                 |/      |
                 |      (_)
                 |
                 |
                 |
                 |
                _|___
            """
        ,
        """
                  _______
                 |/      |
                 |
                 |
                 |
                 |
                 |
                _|___
            """
    );

    public static String getHangmanPicture(int healthPoints) {
        if (healthPoints <= 0) {
            return PICTURES_BY_HEALTH_POINTS.get(0);
        }
        return PICTURES_BY_HEALTH_POINTS.get(healthPoints);
    }
}
