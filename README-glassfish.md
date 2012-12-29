# retoure-web

## GlassFish

Version: Open Source Edition 3.1.2.2

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

### Instance Configuration

    asadmin --port 9048 --user retoure add-resources glassfish-resources.xml
