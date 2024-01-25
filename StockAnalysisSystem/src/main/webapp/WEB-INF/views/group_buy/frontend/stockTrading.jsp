<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

 <!-- Styles for Login Popup -->
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        input {
            padding: 5px;
        }

        button {
            padding: 8px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>


</head>
<body>
<h2>Stock Trading Interface</h2>

    <form id="stockForm">
        <label for="stockName">Stock Name:</label>
        <input type="text" id="stockName" name="stockName" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required>

        <button type="button" onclick="buyStock()">Buy Stock</button>
        <button type="button" onclick="sellStock()">Sell Stock</button>
    </form>

    <h3>Owned Stock List</h3>
    <table id="stockTable">
        <thead>
            <tr>
                <th>Stock Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <!-- This is where dynamically generated stock records go -->
        </tbody>
    </table>
    
    <script>
        function buyStock() {
            // Handle the logic for buying stocks here, add the transaction record to the table
            const stockName = document.getElementById('stockName').value;
            const quantity = document.getElementById('quantity').value;
            const price = document.getElementById('price').value;

            const table = document.getElementById('stockTable').getElementsByTagName('tbody')[0];
            const row = table.insertRow(table.rows.length);
            const cell1 = row.insertCell(0);
            const cell2 = row.insertCell(1);
            const cell3 = row.insertCell(2);

            cell1.textContent = stockName;
            cell2.textContent = quantity;
            cell3.textContent = price;
        }

        function sellStock() {
            // Handle the logic for selling stocks here, remove the corresponding transaction record from the table
            // This is just an example, actual cases may require more complex logic and data handling
            const table = document.getElementById('stockTable').getElementsByTagName('tbody')[0];
            const rowCount = table.rows.length;

            if (rowCount > 0) {
                table.deleteRow(rowCount - 1); // Remove the last transaction record
            }
        }
    </script>



</body>
</html>