# retoure-web

## GlassFish

Version: Open Source Edition 3.1.2.2

### Update EclipseLink

Download EclipseLink OSGi bundles from http://www.eclipse.org/eclipselink/downloads/index.php, unpack them, copy folder to $GLASSFISH_HOME.

    cd $GLASSFISH_HOME
    
    rm glassfish/modules/org.eclipse.persistence.antlr.jar
    rm glassfish/modules/org.eclipse.persistence.asm.jar
    rm glassfish/modules/org.eclipse.persistence.core.jar
    rm glassfish/modules/org.eclipse.persistence.jpa.jar
    rm glassfish/modules/org.eclipse.persistence.jpa.modelgen.jar
    rm glassfish/modules/org.eclipse.persistence.moxy.jar
    rm glassfish/modules/org.eclipse.persistence.oracle.jar
    
    cp eclipselink-plugins-*/org.eclipse.persistence.antlr_*.jar glassfish/modules/org.eclipse.persistence.antlr.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.asm_*.jar glassfish/modules/org.eclipse.persistence.asm.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.core_*.jar glassfish/modules/org.eclipse.persistence.core.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.jpa_*.jar glassfish/modules/org.eclipse.persistence.jpa.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.jpa.modelgen_*.jar glassfish/modules/org.eclipse.persistence.jpa.modelgen.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.moxy_*.jar glassfish/modules/org.eclipse.persistence.moxy.jar
    cp eclipselink-plugins-*/org.eclipse.persistence.oracle_*.jar glassfish/modules/org.eclipse.persistence.oracle.jar

### Application Resources

    asadmin --port 9048 --user retoure add-resources glassfish-resources.xml

### Configure HTTP Service

Disable HTTP chunking to overcome error JSF1095 (while using JSF Flash) with GlassFish:

    asadmin set server-config.network-config.protocols.protocol.http-listener-1.http.chunking-enabled=false

### Setup Domain

    --portbase
          o Administration port
            portbase + 48
          o HTTP listener port
            portbase + 80
          o HTTPS listener port
            portbase + 81
          o JMS port
            portbase + 76
          o IIOP listener port
            portbase + 37
          o Secure IIOP listener port
            portbase + 38
          o Secure IIOP with mutual authentication port
            portbase + 39
          o JMX port
            portbase + 86
          o JPDA debugger port:
            portbase + 9
          o Felix shell service port for OSGi module management:
            portbase + 66
    
    --domainproperties
    
    --nopassword
    --savemasterpassword
    --usemasterpassword

#### Example

    asadmin create-domain --domaindir /usr/home/rbe/glassfish --portbase 9000 --user retoure --savelogin=true retoure-domain
    asadmin --port 9048 --user retoure enable-secure-admin
    asadmin --port 9048 --user retoure create-file-user --authrealmname admin-realm --groups asadmin retoure-admin
    asadmin --port 9048 --user retoure create-password-alias retoure-admin-pwdalias
    asadmin --port 9048 --user retoure enable-secure-admin-internal-user --passwordalias retoure-admin-pwdalias retoure-admin
