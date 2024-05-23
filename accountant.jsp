<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>A Servlet-Based Multi-Tiered Enterprise Application using Tomcat Container</title>
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

    /* Style for the operation selection box */
    .operation-box {
      background-color: #ccc; /* Darker grey for the box */
      padding: 10px;
      border: 1px solid #bbb;
      margin: 10px auto;
      width: 50%;
    }
  </style>

</head>
<body>
  <div class="client">
    <h1>A Servlet-Based Multi-Tiered Enterprise Application using Tomcat Container</h1>
    <h2>You are connected to the Project 4 Enterprise System Database as an accountant-level user.</h2>
    <p>Please select the operation you would like to perform from the list below:</p>

    <form action="clientServlet" method="post">
      <div class="operation-box">
        <ul>
          <li>
            <input type="radio" name="operation" value="max_supplier_status"> Get the maximum status value of all suppliers (return a max value)
          </li>
          <li>
            <input type="radio" name="operation" value="total_part_weight"> Get the total weight of all parts (returns a sum)
          </li>
          <li>
            <input type="radio" name="operation" value="total_shipments"> Get the total number of shipments (return the current number of shipments in total)
          </li>
          <li>
            <input type="radio" name="operation" value="job_with_most_workers"> Get the name and number of workers of the job with the most workers (return two values)
          </li>
          <li>
            <input type="radio" name="operation" value="list_suppliers"> List the name and status of every supplier (returns a list of supplier names with status)
          </li>
        </ul>
      </div>

      <br>

      <button type="submit" id="console">Execute Command</button>
      <button type="button" id="console" onclick="$('#textBox').html('');">Clear Results</button>
    </form>
    <div class="message">
      All execution results will appear below this line:
    </div>
    <div><%= request.getAttribute("message") != null ? request.getAttribute("message") : "No data yet." %></div>
  </div>
</body>
</html>
