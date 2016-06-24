apply plugin: 'java'
apply plugin: 'war'

sourceCompatibility = 1.7
targetCompatibility = 1.7

task wrapper(type: Wrapper) {
    gradleVersion = "2.6"
}

if (isBuildingBlock()) {
    version = getB2Version()
}

repositories {
    mavenCentral()
    maven {
        url "https://bbprepo.blackboard.com/content/repositories/releases/"
    }
}

configurations {
    b2deploy
}

dependencies {
    b2deploy 'org.oscelot:b2deploy-task:0.1.0'

    compile 'org.slf4j:slf4j-api:1.7.5'
    compile 'org.slf4j:jul-to-slf4j:1.7.5'
    compile 'ch.qos.logback:logback-classic:1.1.3'
    compile 'org.slf4j:log4j-over-slf4j:1.7.21'

    compile 'com.thoughtworks.xstream:xstream:1.4.7'
    compile ('net.sourceforge.stripes:stripes:1.6.0') {
        exclude module: 'log4j' // The log4j interface is implemented by org.slf4j:log4j-over-slf4j
    }
    compile 'org.springframework:spring-beans:4.3.0.RELEASE'
    compile 'org.springframework:spring-web:4.3.0.RELEASE'
    compile files('lib/bb-stripes-utils-1.1.0.jar')
    compile files('lib/b2-config-utils-1.0.3.jar')

    providedCompile 'javax.servlet:servlet-api:2.5'
    providedCompile 'jstl:jstl:1.2'
    providedCompile 'blackboard.platform:bb-platform:9.1.201404.160205'
    providedCompile 'blackboard.platform:bb-taglibs:9.1.201404.160205'
    providedCompile 'com.google.guava:guava:16.0'

    /* the bb-cms-admin is needed for the CourseEventHandler Class, as referenced in bb-manifest.xml */
    providedCompile 'blackboard.platform:bb-cms-admin:9.1.201404.160205'

    testCompile "junit:junit:4.11"
}
ant.taskdef(name: 'b2deploy', classname: 'org.oscelot.ant.B2DeployTask', classpath: configurations.b2deploy.asPath)

task deployb2(dependsOn: 'war') << {
    println "Deploying \"" + war.archivePath + "\""
    ant.b2deploy(localfilepath: war.archivePath,
    host: 'localhost:9876',
    clean: 'true',
    courseorgavailable: 'true')
}


String getB2Version() {
    File mfFile = new File(file(webAppDir), 'WEB-INF' + File.separator + 'bb-manifest.xml');
    def manifest = new XmlSlurper().parse(mfFile);
    return manifest.plugin.version['@value'];
}

boolean isBuildingBlock() {
    File mfFile = new File(file(webAppDir), 'WEB-INF' + File.separator + 'bb-manifest.xml');
    return mfFile.exists();
}