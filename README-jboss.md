# retoure-web

## JBoss

Version: 7.1.1.Final

### Datasource Configuration

    <subsystem xmlns="urn:jboss:domain:datasources:1.0">
        <datasources>
            <datasource jta="true" jndi-name="java:jboss/datasources/retoureDatasource" pool-name="retoureDatasourcePool" enabled="true" use-java-context="true" use-ccm="true">
                <connection-url>jdbc:mysql://localhost:3306/retoure</connection-url>
                <driver>com.mysql</driver>
                <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                <pool>
                    <min-pool-size>10</min-pool-size>
                    <max-pool-size>100</max-pool-size>
                    <prefill>true</prefill>
                </pool>
                <security>
                    <user-name>retoure</user-name>
                    <password>retoure</password>
                </security>
                <statement>
                    <prepared-statement-cache-size>32</prepared-statement-cache-size>
                    <share-prepared-statements>true</share-prepared-statements>
                </statement>
            </datasource>
            <drivers>
                <driver name="com.mysql" module="com.mysql">
                    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>

### SMTP Configuration

    <subsystem xmlns="urn:jboss:domain:mail:1.0">
        <mail-session jndi-name="java:/retouren-smtp">
            <smtp-server ssl="true" outbound-socket-binding-ref="retouren-smtp">
                <login name="mail@retourenportal.com" password="xxx"/>
            </smtp-server>
        </mail-session>
    </subsystem>

    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
        <outbound-socket-binding name="retouren-smtp">
            <remote-destination host="mail.1ci.net" port="465"/>
        </outbound-socket-binding>
    </socket-binding-group>
