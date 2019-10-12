package com.epam.fitness.tag.localizer;

import com.epam.fitness.entity.assignment.AssignmentStatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class AssignmentStatusLocalizerTag extends BodyTagSupport {

    private static final String NEW_STATUS = "new_status";
    private static final String ACCEPTED_STATUS = "accepted_status";
    private static final String CHANGED_STATUS = "changed_status";
    private static final String CANCELED_STATUS = "canceled_status";

    private JspWriter writer;

    @Override
    public int doStartTag(){
        writer = pageContext.getOut();
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        String value = bodyContent.getString();
        String attributeName = getAttributeName(value);
        try{
            Object attribute = pageContext.findAttribute(attributeName);
            writer.print(attribute);
        } catch (IOException ex){
            throw new JspException(ex.getMessage(), ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private String getAttributeName(String statusValue) throws JspException{
        AssignmentStatus status = AssignmentStatus.valueOf(statusValue);
        String attributeName;
        switch (status){
            case NEW:
                attributeName = NEW_STATUS;
                break;
            case ACCEPTED:
                attributeName = ACCEPTED_STATUS;
                break;
            case CHANGED:
                attributeName = CHANGED_STATUS;
                break;
            case CANCELED:
                attributeName = CANCELED_STATUS;
                break;
            default:
                throw new JspException("Invalid status value: " + statusValue);
        }
        return attributeName;
    }

}