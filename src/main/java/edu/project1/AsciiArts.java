package edu.project1;

import java.util.List;

public class AsciiArts {
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

    private static final List<String> ARTS_BY_HEALTH_POINTS = List.of(
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

    public static String getArtByHealthPoints(int healthPoints) {
        return ARTS_BY_HEALTH_POINTS.get(healthPoints);
    }
}
