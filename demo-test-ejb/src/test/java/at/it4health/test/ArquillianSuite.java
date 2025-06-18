package at.it4health.test;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.ConfigurableMavenResolverSystem;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@ArquillianSuiteDeployment
public class ArquillianSuite extends Arquillian {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite called");
    }

    @Deployment(name = "app_services", testable = true)
    public static Archive<?> createDeployment() {

        System.out.println("createDeployment called... ");

        ConfigurableMavenResolverSystem mavenResolver = Maven.configureResolver().workOffline(); // dont enable online it tries to load the jars from nexus/maven.central
        PomEquippedResolveStage pom = mavenResolver.loadPomFromFile("pom.xml");
        System.out.println(" Pom file loaded: " + pom.toString());

        MavenFormatStage specificRuntimeLibs = pom.importRuntimeAndTestDependencies().resolve().withTransitivity();
        System.out.println("maven specificRuntimeLibs:  " + specificRuntimeLibs.toString());
        File[] serverlibs = specificRuntimeLibs.asFile();

        System.out.println("runtime - test files: " + Arrays.stream(serverlibs).map(File::getName).collect(Collectors.toList()));

        return ShrinkWrap.create(WebArchive.class, "test.war")
                //add all deps with scope compile||runtime and some test dependencies (but not all since we don't want arquillian in the WARs
                .addAsLibraries(
                        Maven.configureResolver().workOffline()
                                .loadPomFromFile("pom.xml")
                                //                             .importTestDependencies()
                                .importRuntimeDependencies()
                                .resolve()
//                                .resolve(
//                                        "org.assertj:assertj-core",
//                                        "io.rest-assured:rest-assured",
//                                )
                                .withTransitivity()
                                .as(File.class)
                )
                //add files from WEB-iNF and META-INF
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), ArchivePaths.create("beans.xml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jboss-deployment-structure.xml"), ArchivePaths.create("jboss-deployment-structure.xml"))
                // included inside jar .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                //.addAsWebInfResource(new File("src/test/resources/logging.properties"), "logging.properties")
                //special datasource for initial tests
                //.addAsManifestResource("jbossas-ds.xml")
                //add all classes from this maven module
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory("target/classes")
                        .importDirectory("target/test-classes")
                        .as(GenericArchive.class), "WEB-INF/classes");

    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite called");
    }
}
