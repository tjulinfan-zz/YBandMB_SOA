YBandMB_SOA
===========

Web service and SOA Final project


## 配置

### Javamail 配置

	JNDI Name: mail/smtpserver
	Mail Host: <smtp server>
	Default User: <username@server.com>
	Default Return <username@server.com>
	
	Additional Properties:
		mail-password: ***
		mail-auth: true
		mail-port: ***


### JMS 配置
	
	Configuration -> Physical Destinations
	sendingEmailQueue : queue

	Resources -> JMS Resources -> Destination Resources
	JNDI Name: jms/sendingEmailQueue
	Resource Type: javax.jms.Queue