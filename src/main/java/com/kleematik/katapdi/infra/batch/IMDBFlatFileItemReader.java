package com.kleematik.katapdi.infra.batch;

import com.kleematik.katapdi.infra.batch.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

import java.time.Instant;

@Slf4j
public class IMDBFlatFileItemReader extends FlatFileItemReader<Movie> {

    private static final int HEADER = 1;

    public IMDBFlatFileItemReader(LineMapper defaultLineMapper) {
        super();
        setLinesToSkip(HEADER);
        setLineMapper(defaultLineMapper);
    }

    public static class LineMapper extends DefaultLineMapper<Movie> {

        private static final String T_CONST = "tconst";
        private static final String TITLE_TYPE = "titleType";
        private static final String PRIMARY_TITLE = "primaryTitle";
        private static final String ORIGINAL_TITLE = "originalTitle";
        private static final String IS_ADULT = "isAdult";
        private static final String START_YEAR = "startYear";
        private static final String END_YEAR = "endYear";
        private static final String RUNTIME_MINUTES = "runtimeMinutes";
        private static final String GENRES = "genres";
        private static final String DELIMITER = "\t";

        public LineMapper() {
            super();
            setLineTokenizer(lineTokenizer());
            setFieldSetMapper(fieldSetMapper());
        }

        private LineTokenizer lineTokenizer() {
            final var delimitedLineTokenizer = new DelimitedLineTokenizer();
            delimitedLineTokenizer.setDelimiter(DELIMITER);
            delimitedLineTokenizer.setNames(T_CONST, TITLE_TYPE, PRIMARY_TITLE, ORIGINAL_TITLE,
                    IS_ADULT, START_YEAR, END_YEAR, RUNTIME_MINUTES, GENRES);
            delimitedLineTokenizer.setStrict(false);
            return delimitedLineTokenizer;
        }

        private FieldSetMapper<Movie> fieldSetMapper() {
            return fieldSet -> Movie.builder()
                    .tconst(fieldSet.readString(T_CONST))
                    .titleType(fieldSet.readString(TITLE_TYPE))
                    .primaryTitle(fieldSet.readString(PRIMARY_TITLE))
                    .originalTitle(fieldSet.readString(ORIGINAL_TITLE))
                    .isAdult(fieldSet.readBoolean(IS_ADULT))
                    .startYear(fieldSet.readString(START_YEAR))
                    .endYear(fieldSet.readString(END_YEAR))
                    .runtimeMinutes(fieldSet.readString(RUNTIME_MINUTES))
                    .genres(fieldSet.readString(GENRES))
                    .timestamp(Instant.now())
                    .build();
        }

    }
}
