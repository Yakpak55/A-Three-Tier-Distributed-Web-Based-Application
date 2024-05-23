<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- RootHome.jsp -->
<html lang="en">
<head>
    <title>CNT 4714 Spring 2024 - Project 4 Enterprise System - Root</title>
    <meta charset="utf-8"/>
    <style type="text/css">
        body {
            background: black; /* Changed background color */
            color: white; /* Changed text color for contrast */
            text-align: center;
            font-family: Arial;
        }

        h1 {
            color: red;
            font-size: 28pt;
        }

        h2 {
            color: cyan;
            font-size: 24pt;
        }

        input, textarea, button {
            font-size: 16pt;
            padding: 10px;
        }

        input[type="submit"], button {
            color: lime;
            background: #444; /* Darker background for buttons */
            border: 1px solid lime; /* Style border to make buttons stand out */
        }

        input[type="reset"], button[type="button"] {
            color: red;
            background: #444;
            border: 1px solid red;
        }

        textarea {
            background: #333; /* Darker textarea background */
            color: white;
            font-family: Verdana;
            width: 90%; /* Adjusted for better responsiveness */
            height: 150px; /* Adjusted height */
        }

        .highlight {
            color: red;
        }

        .message {
            color: green;
            margin-top: 20px;
        }

        #console {
            margin: 10px;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script type="text/javascript">
        function eraseText() {
            $("#textBox").html("");
        }
    </script>
</head>
<body>
    <div class="root">
        <h1>CNT 4714 Spring 2024 - Project 4 Enterprise System - Root</h1>
        <p>You are connected to the Project 4 Enterprise System Database as a <span class="highlight">root-level</span> user.</p>
        <p><strong>Please enter a valid SQL query or update command.</strong></p>
        
        <form action="rootServlet" method="post">
            <textarea name="sqlStatement" id="textBox" placeholder="Enter SQL Command Here"><%= request.getAttribute("sqlStatement") != null ? request.getAttribute("sqlStatement") : "" %></textarea>
            <br>
            <button type="submit" id="console">Execute Command</button>
            <button type="reset" id="console" onclick="eraseText()">Reset Form</button>
            <button type="button" id="console" onclick="$('#textBox').html('');">Clear Form</button>
        </form>
        <div class="message">
            All execution results will appear below:
            <div><%= request.getAttribute("message") != null ? request.getAttribute("message") : "No data yet." %></div>
        </div>
    </div>
</body>
</html>
