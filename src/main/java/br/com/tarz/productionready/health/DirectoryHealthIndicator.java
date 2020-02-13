package br.com.tarz.productionready.health;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DirectoryHealthIndicator implements HealthIndicator {
	private static final Logger LOG = LoggerFactory.getLogger(DirectoryHealthIndicator.class);

	private File directory;
	private String pathFile;

	@Autowired
	public void DirectoryInputHealthIndicator(
			@Value("${health.directory.path:${production-ready.diretorio}}") String path) throws IOException {
		this.directory = new File(path);
		this.pathFile = path;
	}


	@Override
	public Health health() {
		LOG.info("Checking PATH...");
		if (Files.isDirectory(Paths.get(pathFile))) {
			LOG.info("Directory Input: " + directory.getAbsolutePath() + " exists.");
			return Health.up().withDetail("directory.name", directory.getAbsolutePath()).build();
		} else {
			LOG.warn("Directory Input: " + directory.getAbsolutePath() + " not exists.");
			return Health.down()
					.withDetail("directory.error", "Directory: " + directory.getAbsolutePath() + " not exists.")
					.build();
		}
	}

}
