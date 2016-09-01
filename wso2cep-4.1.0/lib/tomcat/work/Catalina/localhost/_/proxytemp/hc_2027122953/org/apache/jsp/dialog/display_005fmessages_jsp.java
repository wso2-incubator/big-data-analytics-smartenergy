/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-09-01 09:09:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.dialog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.wso2.carbon.ui.CarbonUIMessage;
import org.wso2.carbon.ui.util.CharacterEncoder;

public final class display_005fmessages_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    var msgId;\n");
      out.write("    ");

    if(CharacterEncoder.getSafeText(request.getParameter("msgId")) == null){
    
      out.write("\n");
      out.write("    msgId = '");
      out.print("MSG" + System.currentTimeMillis() + Math.random());
      out.write("';\n");
      out.write("    ");

    } else {
    
      out.write("\n");
      out.write("    msgId = '");
      out.print(CharacterEncoder.getSafeText(request.getParameter("msgId")));
      out.write("';\n");
      out.write("    ");

    }
    
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");

    //First checks whether there is a CarbonUIMessage in the request
    CarbonUIMessage carbonMessage = (CarbonUIMessage) session.getAttribute(CarbonUIMessage.ID);

    if(carbonMessage == null){
        carbonMessage = (CarbonUIMessage) request.getAttribute(CarbonUIMessage.ID);
    } else {
        session.removeAttribute(CarbonUIMessage.ID);
    }

    if (carbonMessage != null) {
        String message = carbonMessage.getMessage();
        String messageType = carbonMessage.getMessageType();
        if (message == null || message.equals("") || messageType == null) {
        } else {
            if (messageType.equals(CarbonUIMessage.INFO)) {

      out.write("\n");
      out.write("            <script type=\"text/javascript\">\n");
      out.write("                jQuery(document).ready(function() {\n");
      out.write("                    if (getCookie(msgId) == null) {\n");
      out.write("                        CARBON.showInfoDialog(\"");
      out.print( carbonMessage.getMessage());
      out.write("\");\n");
      out.write("                        setCookie(msgId, 'true');\n");
      out.write("                    }                    \n");
      out.write("                });\n");
      out.write("\n");
      out.write("            </script>\n");

            } else if (messageType.equals(CarbonUIMessage.WARNING)) {

      out.write("\n");
      out.write("            <script type=\"text/javascript\">\n");
      out.write("                jQuery(document).ready(function() {\n");
      out.write("                    if (getCookie(msgId) == null) {\n");
      out.write("                        CARBON.showWarningDialog(\"");
      out.print( carbonMessage.getMessage());
      out.write("\");\n");
      out.write("                        setCookie(msgId, 'true');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            </script>\n");

            } else if (messageType.equals(CarbonUIMessage.ERROR)) {

      out.write("\n");
      out.write("            <script type=\"text/javascript\">\n");
      out.write("                jQuery(document).ready(function() {\n");
      out.write("                    if (getCookie(msgId) == null) {\n");
      out.write("                        CARBON.showErrorDialog(\"");
      out.print( carbonMessage.getMessage());
      out.write("\");\n");
      out.write("                        setCookie(msgId, 'true');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            </script>\n");

            }
        }
    }

      out.write('\n');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}