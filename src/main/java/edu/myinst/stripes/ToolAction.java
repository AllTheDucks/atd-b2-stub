package ${basePackage}.stripes;

import com.alltheducks.bb.stripes.EntitlementRestrictions;
import com.alltheducks.bb.stripes.LoginRequired;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

@LoginRequired
@EntitlementRestrictions(entitlements = "${vendorId}.${b2Handle}.admin.MODIFY", errorPage = "/error.jsp")
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