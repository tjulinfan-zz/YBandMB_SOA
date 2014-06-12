//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.5-2 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.06.12 时间 04:36:56 PM CST 
//


package com.potatoni.emailentity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.potatoni.emailentity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmailEntity_QNAME = new QName("http://www.potatoni.com/mailsystem/emailentity", "EmailEntity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.potatoni.emailentity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmailEntityComplexType }
     * 
     */
    public EmailEntityComplexType createEmailEntityComplexType() {
        return new EmailEntityComplexType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailEntityComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.potatoni.com/mailsystem/emailentity", name = "EmailEntity")
    public JAXBElement<EmailEntityComplexType> createEmailEntity(EmailEntityComplexType value) {
        return new JAXBElement<EmailEntityComplexType>(_EmailEntity_QNAME, EmailEntityComplexType.class, null, value);
    }

}
