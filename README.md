# A-Three-Tier-Distributed-Web-Based-Application
 Developed a comprehensive three-tier web-based application using Java, JSP, and Tomcat to interact with a MySQL database. 

The project involved:

● Authentication System: Created a landing page for user authentication, validating credentials against a credentials database. Successful logins redirected users to appropriate interfaces based on their roles (root, client, data entry, accountant).

● Root and Client Interfaces: Designed JSP pages for root and client users to execute SQL commands, with buttons for executing commands, resetting forms, and clearing results. Handled SQL queries and updates with proper feedback.

● Data Entry Interface: Developed a JSP page for data entry users to insert data into specific tables using prepared statements. Included forms for suppliers, parts, jobs, and shipments, with automatic parameter extraction and execution.

● Accountant Interface: Created a JSP page for accountant users to generate reports via remote stored procedures using the CallableStatement interface. Provided a list of report options for selection.

● Business Logic Implementation: Implemented business logic in servlets to update supplier status based on shipment quantities. Enhanced logic in bonus implementation to update only affected suppliers.

● Error Handling and Metadata: Ensured proper error handling and displayed metadata for all query results. Provided clear feedback for any issues encountered during SQL command execution.

Skills: JavaScript · Java · JavaServer Pages (JSP) · Servlets · Java Database Connectivity (JDBC) · MySQL · Back-End Web Development · Web Development · Authentication Systems · Data Management · Software Development Lifecycle · Object-Oriented Design
