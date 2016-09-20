# Blackboard Building Block Template #

This is a skeleton building block which can be used to bootstrap the creation of a Blackboard Building Block.

A building block created using this stub will, by default, include:
 - [Spring Beans](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html) for dependency injection
 - [Stripes Framework](https://stripesframework.atlassian.net/wiki/display/STRIPES/Home) for MVC
 - [ATD's Configuration Utilities Library](https://github.com/AllTheDucks/b2-config-utils) with example configuration page
 - [ATD's Bundle Utilities Library](https://github.com/AllTheDucks/b2-bundle-utils)
 - [SLF4J](http://www.slf4j.org/), [Logback](http://logback.qos.ch/) and [ATD's Logging Utilities Library](https://github.com/AllTheDucks/b2-logging-utils) for logging.

When initialising your project, you will be given the option to include:
 - A Blackboard Course Event Listener
 - [Schema.xml](https://blog.alltheducks.com/post/introduction_to_blackboard_schema_xml/) including [example Beans and DAOs](https://blog.alltheducks.com/post/blackboard_ORM_framework_part_two/)
 - A course and/or system tool

The project uses the following development tools:
 - [Gradle](https://gradle.org/)
 - Deploy B2 ANT Task for rapid deployment to your development environment

This is the project we use at [All the Ducks](https://www.alltheducks.com/) to bootstrap all of our  Blackboard Building Block projects. It has been used a lot in high volume production environments.


# Creating a new Project #

1. Get the template project:
````git clone https://github.com/AllTheDucks/atd-b2-stub.git myproject````
where ````myproject```` is the name of your project.
2. Change into the project directory: ````cd myproject````
3. Run the initB2 task and answer all the questions: ````gradlew initB2```` 
4. Add a remote for your new git project.: ````git remote add origin <git url>````

**DONE !**


# Stuff you might like to try #

## Create a page using MVC ##
The B2 stub uses [Stripes Framework](https://stripesframework.atlassian.net/wiki/display/STRIPES/Home) for MVC. In this project, Stripes is configured to search for `ActionBean`s in the `stripes` sub-package of your project's package. It will recurse this package, so you can have any structure you please within this sub-package.

So the first thing to do is, create a class which implements ActionBean:
````Java
public class MyActionBean implements ActionBean {

    private BlackboardActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }

    @Override
    public BlackboardActionBeanContext getContext() {
        return null;
    }
}
````

Now create an action on your ActionBean:
````Java
public class MyActionBean implements ActionBean {

    private BlackboardActionBeanContext context;

    private String myString;

    @DefaultHandler
    public Resolution myAction() {
        // Do any work that needs to happen.
        // Load stuff into properties on the bean, making sure those properties
        // have getters (and setters if you are accepting post backs).
        this.myString = "My happy string!"
        return new ForwardResolution("/WEB-INF/jsp/my.jsp");
    }

    public String getMyString() {
        return myString;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }

    @Override
    public BlackboardActionBeanContext getContext() {
        return null;
    }
}
````

Now render your page using JSP:
````JSP
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<fmt:message var="message" key="my.example.language.pack.key" />

<bbNG:learningSystemPage ctxId="ctx">
    ${message}: ${actionBean.myString}
</bbNG:learningSystemPage>
````

The [Stripes Framework Documentation](https://stripesframework.atlassian.net/wiki/display/STRIPES/Home) provides more details.

## Bind an action to a pretty URL ##
You can use Stripes to bind pretty URLs to your `ActionBean`s. To do this, you annotate your `ActionBean` with `@UrlBinding`:

````Java
@UrlBinding("/myaction")
public class MyActionBean implements ActionBean {
    ...
}
````

Now you need to make sure that requests to this path are forwarded to the the `DispatcherServlet`. In the `src/main/webapp/WEB-INF/web.xml` file ensure that there is a `<servlet-mapping>` that matches this path:

````XML
<servlet-mapping>
    <servlet-name>StripesDispatcher</servlet-name>
    <url-pattern>/myaction</url-pattern>
</servlet-mapping>
````

You may wish to use a URL pattern to avoid creating a new `<servlet-mapping>` for each `ActionBean`. As an example, you might wish to map all paths that start with `/action` to the `DispatcherServlet` and then use `@UrlBinding`s that match the URL pattern:

````XML
<servlet-mapping>
    <servlet-name>StripesDispatcher</servlet-name>
    <url-pattern>/actions/*</url-pattern>
</servlet-mapping>
````

````Java
@UrlBinding("/actions/my")
public class MyActionBean implements ActionBean {
    ...
}
````

## Inject a bean using Spring dependency injection ##
The B2 stub uses [Spring Beans](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html) for dependency injection. 

There are a number of different methods for building your dependency graph. At [All the Ducks](https://www.alltheducks.com/) we prefer manually wire everything together using XML as it is more explicit and thus more obvious what is going on. This doesn't mean you cannot use annotations if you choose.

To see how all the beans that are required for the project, open `src/main/webapp/WEB-INF/applicationContext.xml`. 

Documentation on using the [Spring Beans framework is available](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html).

Injecting beans into your Stripes `ActionBean`s cannot be done using this method. This is because instances of these beans are constructed by Stripes, not Spring. However, you can tell Stripes to inject your beans into the `ActionBean`s it creates using the `@SpringBean` annotation:

````Java
@SpringBean
private ConfigurationService<Configuration> configService;

@DefaultHandler
public Resolution myAction() {
    Configuration config = configService.loadConfiguration();
    //Do something with the config.
    return new ForwardResolution("/WEB-INF/jsp/my.jsp");
}
````

## Create a configuration item ##
The B2 stub uses [ATD's Configuration Utilities Library](https://github.com/AllTheDucks/b2-config-utils) to provide configuration services to the building block. This building block handles the saving, loading and caching of a configuration bean.

To add a configuration item, you first must add a property to the `Configuration` bean:
````Java
public class Configuration {
    
    ...

    private String myString;

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    ...
}
````

Next we'll add a string to the language pack bundle to use as the label. Using a key which maps to the field on the configuration object on the configuration action means Stripes will automatically use this as the name of the field in error messages.
````
com.alltheducks.example.stripes.ConfigAction.config.myString=My String
````

Next, we'll add a field to the configuration page by modifying config.jsp:
````JSP
<fmt:message var="myStringLabel" key="com.alltheducks.example.stripes.ConfigAction.config.myString" />

...

<bbNG:dataElement isRequired="true" label="${myStringLabel}">
    <stripes:text name="config.myString"/>
    <stripes:errors field="config.myString"/>
</bbNG:dataElement>
````

We should also add validation to in the `ConfigAction`:
````Java
@ValidateNestedProperties({@Validate(field = "myString", required = true))
private Configuration config;
````

If you would like your configuration item to have a default value, open up `src/main/resources/defaultConfig.xml` and add a default value to the XML:
````XML
<com.alltheducks.example.config.Configuration>
  ...
  <myString>My happy string!</myString>
  ...
</com.alltheducks.example.config.Configuration>
````

## Log something ##
Logging in the B2 stub is achieved through the [SLF4J](http://www.slf4j.org/) logging API. The actual implementation of this API is provided by [Logback](http://logback.qos.ch/). Also [ATD's Logging Utilities Library](https://github.com/AllTheDucks/b2-logging-utils) is used to support Blackboards SaaS's logging requirements. However, all that needs to be known to achieve basic logging, is the SLF4J API.

On whichever class you wish to do logging initialise a logger, making sure you substitute your own classes name:
````Java
public class MyClass {
	private final static Logger logger = LoggerFactory.getLogger(MyClass.class);
}
````

Now you can use that logger from anywhere in your class' code:
````Java
public void myMethod() {
    logger.debug("About to do stuff");
    
    try {
        // something that might throw an exception
    } catch (Exception e) {
        logger.error("Something went wrong...", e);
    }
}
````

The default configuration of building blocks created from this stub is to log to `blackboard/logs/plugins/atd-example/atd-example.log` with daily rolling, where `atd-example` is the vendor ID and handle of your building block.

## Get a language pack message ##
Obtaining a language pack message from the bundle varies depending on where you are trying to get it.

### JSP ###
JSP has a built-in mechanism for obtaining language pack keys. Simply include the `fmt` tag library and get messages using the `fmt:message` tag:
````JSP
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
...
<fmt:message var="myString" key="some.example.language.pack.key" />
````

If your message has parameters, this use the `fmt:param` tag:
````JSP
<fmt:message var="myString" key="some.example.language.pack.key">
    <fmt:param value="My Param Value" />
</fmt:message>
````

### Java ###
To obtain a message in Java, use the `BundleService` provided by [ATD's Bundle Utilities Library](https://github.com/AllTheDucks/b2-bundle-utils). The stub initialisation should have already created one for you in Spring, so it just needs to be injected into your class as described above. Then use the `getLocalisationString` method to get your message:

````Java
bundleService.getLocalisationString("some.example.language.pack.key");
````

Got parameters?:
````Java
bundleService.getLocalisationString("some.example.language.pack.key", "My Param Value");
````

### Javascript ###
[ATD's Bundle Utilities Library](https://github.com/AllTheDucks/b2-bundle-utils) provides a mechanism for retrieving messages from the language pack in your Javascript too:

By default, the servlet required for this is not installed. It is however, commented out in the `web.xml` ready to be added:
````XML
<servlet>
    <servlet-name>JsBundleServlet</servlet-name>
    <servlet-class>com.alltheducks.bundleutils.JsBundleServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>JsBundleServlet</servlet-name>
    <url-pattern>/bundle.js</url-pattern>
</servlet-mapping>
````

Once this servlet is listening, add an include to `bundle.js` to your page and access your messages using `atd.bundles['atd-example'].getString`, where `atd-example` is the vendor ID and handle of your building block.
:
````HTML
<script type="text/javascript" src="bundle.js"></script>
<script type="text/javascript">
	var myString = atd.bundles['atd-example'].getString("some.example.language.pack.key");
</script>
````

Parameters are supported in Javascript too:
````HTML
<script type="text/javascript">
	var myString = atd.bundles['atd-example'].getString("some.example.language.pack.key", "My Param Value");
</script>
````

