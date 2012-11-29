# retoure-web

## Web Server

### Virtual Host Configuration 

    <VirtualHost *:80>
        ServerName www.retourenportal.com
        ServerAdmin webmaster@retourenportal.com
        DocumentRoot /usr/home/retoure/apache/sites/retourenportal.com/www/
        <IfModule mod_rewrite.c>
            RewriteEngine On
            Options FollowSymLinks
            # We use www.retourenportal.com only
            RewriteCond %{HTTP_HOST} !^www.retourenportal.com$
            RewriteRule ^(.*)$ http://www.retourenportal.com/$1 [L,R=301]
            # Proxy Java App
            #RewriteRule /retoure-web/(.*) http://127.0.0.1:8080/retoure-web/$1 [P,L]
        </IfModule>
        <IfModule mod_proxy.c>
            ProxyRequests Off
            ProxyTimeout 3
            <Proxy *>
                Order deny,allow
                Allow from all
            </Proxy>
            ProxyPass /retoure-web/ ajp://127.0.0.1:8009/retoure-web/
            ProxyPassReverse /retoure-web/ ajp://127.0.0.1:8009/retoure-web/
            #ProxyPass / ajp://127.0.0.1:8009/retoure-web/
            #ProxyPassReverse / ajp://127.0.0.1:8009/retoure-web/
        </IfModule>
    </VirtualHost>

    <VirtualHost *:80>
        ServerName files.retourenportal.com
        ServerAdmin webmaster@retourenportal.com
        DocumentRoot /usr/home/retoure/apache/sites/retourenportal.com/www/files
        <IfModule mod_rewrite.c>
            RewriteEngine On
            Options FollowSymLinks
            # We use files.retourenportal.com only
            RewriteCond %{HTTP_HOST} !^files.retourenportal.com$
            RewriteRule ^(.*)$ http://files.retourenportal.com/$1 [L,R=301]
        </IfModule>
    </VirtualHost>
