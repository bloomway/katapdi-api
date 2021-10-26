package com.kleematik.katapdi.domain.movie;

import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Movie {
    private Long id;
    private Tconst tconst;
    private Title title;
    private boolean isAdult;
    private Release release;
    private Duration duration;
    private List<Genre> genres;

    @ToString
    @Builder
    @Getter
    public static class Title {
        private final String original;
        private final String primary;
        private final String type;
    }

    @ToString
    @RequiredArgsConstructor(staticName = "of")
    @Getter
    public static class Tconst {
        private final String value;
    }

    @ToString
    @RequiredArgsConstructor(staticName = "of")
    @Getter
    public static class Genre {
        private final String value;
    }

    @ToString
    @RequiredArgsConstructor(staticName = "of")
    @Getter
    public static class Duration {
        private final String value; // runtimeMinutes
    }

    @ToString
    @Getter
    @Builder
    public static class Release {
        private final String start;
        private final String end;
    }
}
