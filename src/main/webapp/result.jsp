<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>EMI Result</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <div class="container result-box" id="result-box">
        <h1>💰 EMI Calculation Result</h1>

        <div class="result-group">
            <p><strong>Loan Amount:</strong> ₹<%= request.getParameter("loanAmount") %></p>
            <p><strong>Repayment Duration:</strong> <%= request.getParameter("timeDuration") %> months</p>
            <p><strong>Interest Rate:</strong> <%= request.getParameter("rate") %> %</p>
        </div>

        <div class="result-final">
            <p><strong>Total EMI:</strong> ₹<%= request.getAttribute("emi") %></p>
        </div>

        <div style="margin-top: 30px;">
            <button class="download-btn" onclick="downloadPDF()">⬇️ Download as PDF</button>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <a href="index.html"><button>🔁 Calculate Again</button></a>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
    <script>
        function downloadPDF() {
            const element = document.getElementById("result-box");
            html2pdf().from(element).save("EMI_Result.pdf");
        }
    </script>

</body>
</html>
