package edu.myinst.stripes;

import blackboard.platform.plugin.PlugInUtil;
import com.alltheducks.bb.stripes.EntitlementRestrictions;
import com.alltheducks.bb.stripes.LoginRequired;
import edu.myinst.config.Configuration;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.alltheducks.configutils.service.ConfigurationService;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@LoginRequired
@EntitlementRestrictions(entitlements = "myu.stub.admin.MODIFY", errorPage = "error.jsp")
public class ConfigAction implements ActionBean {

    private ActionBeanContext context;

    Logger logger = LoggerFactory.getLogger(ConfigAction.class);

    @ValidateNestedProperties({@Validate(field = "settingOne", required = true),
            @Validate(field = "settingTwo", required = true, minvalue = 0, maxvalue = 100)})
    private Configuration config;

    @SpringBean
    private ConfigurationService<Configuration> configService;

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadConfiguration() {
        config = configService.loadConfiguration();
        if (config == null) {
            config = new Configuration();
        }

    }


    @DefaultHandler
    @DontValidate
    public Resolution displayConfigPage() {
        logger.info("THIS IS A TEST THIS IS A TEST THIS IS A TES THIS IS A TEST");
        return new ForwardResolution("/WEB-INF/jsp/config.jsp");
    }

    public Resolution saveConfiguration() {
        configService.persistConfiguration(config);
        return new RedirectResolution(PlugInUtil.getPlugInManagerURL(), false);
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
