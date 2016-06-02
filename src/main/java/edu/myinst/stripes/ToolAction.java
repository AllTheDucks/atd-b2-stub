package edu.myinst.stripes;

import com.alltheducks.bb.stripes.EntitlementRestrictions;
import com.alltheducks.bb.stripes.LoginRequired;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

@LoginRequired
@EntitlementRestrictions(entitlements = "myu.stub.admin.MODIFY", errorPage = "/error.jsp")
public class ToolAction implements ActionBean {

    private ActionBeanContext context;
    	
	public ActionBeanContext getContext() {
        return context;
    }

    @DefaultHandler
    public Resolution displayPage() {
        return new ForwardResolution("/WEB-INF/jsp/tool.jsp");
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
}