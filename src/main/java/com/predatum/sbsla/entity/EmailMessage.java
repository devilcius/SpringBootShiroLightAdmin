package com.predatum.sbsla.entity;

/**
 *
 * @author Marcos Peña
 */
public class EmailMessage
{

    private String body;
    private String sender;
    private String subject;
    private String recipient;

    /**
     * @return the subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param subject the subject recipient set
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * @return the body
     */
    public String getBody()
    {
        return body;
    }

    /**
     * @param body the body recipient set
     */
    public void setBody(String body)
    {
        this.body = body;
    }

    /**
     * @return the from
     */
    public String getSender()
    {
        return sender;
    }

    /**
     * @param from the from recipient set
     */
    public void setSender(String from)
    {
        this.sender = from;
    }

    /**
     * @return the recipient
     */
    public String getRecipient()
    {
        return recipient;
    }

    /**
     * @param to the recipient recipient set
     */
    public void setRecipient(String to)
    {
        this.recipient = to;
    }
}
