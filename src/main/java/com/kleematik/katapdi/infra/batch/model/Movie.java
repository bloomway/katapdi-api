package com.kleematik.katapdi.infra.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tconst;
    @Column(name = "original_title")
    private String originalTitle;
    @Column(name = "primary_title")
    private String primaryTitle;
    @Column(name = "title_type")
    private String titleType;
    @Column(name = "is_adult")
    private boolean isAdult;
    @Column(name = "start_year")
    private String startYear;
    @Column(name = "end_year")
    private String endYear;
    @Column(name = "runtime_minutes")
    private String runtimeMinutes;
    private String genres;
    @Column(name = "date_chargement")
    private Instant timestamp;
}
