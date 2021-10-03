package com.emu.maven.plugins;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Mojo(name = "dependencies-mojo", defaultPhase = LifecyclePhase.COMPILE)
public class DependenciesMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(property = "outputDirectory")
    protected File outputDirectory;

    String generatedSourcesDir() {
        return (outputDirectory != null) ? outputDirectory.getAbsolutePath() :
                project.getBuild().getDirectory() + File.separator + "generated-sources";
    }

    private void createGeneratedSourcesDir() throws MojoExecutionException {
        try {
            FileUtils.forceMkdir(new File(generatedSourcesDir()));
        } catch (IOException e) {
            throw  new MojoExecutionException("Error while creating generatedSourcesDir.",e);
        }
    }
    public void execute() throws MojoExecutionException, MojoFailureException {
        List <Dependency> dependencies = project.getDependencies();
        long numDependencies = dependencies.stream().count();
        getLog().info("Number of dependencies: " + numDependencies);

        createGeneratedSourcesDir();

        try {
            FileUtils.writeLines(new File(generatedSourcesDir()+ "/report.txt"), Collections.singleton("Number of dependencies: " + numDependencies ));
        } catch (IOException e) {
            throw  new MojoExecutionException("Error while wrinting in generatedSourcesDir.",e);
        }

    }






}
