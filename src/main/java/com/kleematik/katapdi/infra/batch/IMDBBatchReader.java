package com.kleematik.katapdi.infra.batch;

import com.kleematik.katapdi.infra.batch.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Arrays;

@Slf4j
public class IMDBBatchReader extends MultiResourceItemReader<Movie> {

    private final String workDir;

    public IMDBBatchReader(
            String workDir,
            IMDBFlatFileItemReader imdbFlatFileItemReader
    ) {
        super();
        this.workDir = workDir;
        this.setDelegate(imdbFlatFileItemReader);
        this.setResources(resources());
    }

    private FileSystemResource[] resources() {
        final var dir = new File(workDir);
        final var files = dir.listFiles();

        if (files == null) return new FileSystemResource[]{};

        return Arrays.stream(files)
                .filter(File::isFile)
                .map(FileSystemResource::new)
                .peek(resource ->
                        log.info("### resource filename=" + resource.getFilename() +
                        ", dir="  + resource.getFile().getParent()))
                .toArray(FileSystemResource[]::new);
    }
}
