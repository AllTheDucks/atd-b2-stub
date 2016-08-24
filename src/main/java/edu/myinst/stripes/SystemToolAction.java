package ${basePackage}.stripes;

import com.alltheducks.bb.stripes.BlackboardActionBeanContext;
import com.alltheducks.bb.stripes.EntitlementRestrictions;
import com.alltheducks.bb.stripes.LoginRequired;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

@LoginRequired
@EntitlementRestrictions(entitlements = "${vendorId}.${b2Handle}.admin.MODIFY", errorPage = "/error.jsp")
public class SystemToolAction implements ActionBean {

    private BlackboardActionBeanContext context;

    @DefaultHandler
    public Resolution displayPage() {
        return new ForwardResolution("/WEB-INF/jsp/system-tool.jsp");
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }
}