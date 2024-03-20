Set objExcel = CreateObject("Excel.Application")
objExcel.Visible = True

WScript.Echo "Hello"
WScript.StdOut.Write "Hello"
WScript.StdOut.WriteLine "Hello"
Stdout.WriteLine "Hello"
Stdout.Write "Hello"

Set objWorkbook = objExcel.Workbooks.Open("c:/jenkins/workspace/CERT_Sanity_SOATest/portCleanUpJob2/src/main/resources/apiChart.xlsx")
Set objWorksheet = objWorkbook.Worksheets(1)

' Assume that the data is in range A1:B10
Set objRange = objWorksheet.Range("B1:C25")

' Create a chart.
Set objChart = objWorkbook.Charts.Add()

With objChart
    .SetSourceData(objRange)
    .ChartType = 4 ' xlLine
    .HasTitle = True
    .ChartTitle.Text = "API Monitor"
    .Axes(1, 1).HasTitle = True
    .Axes(1, 1).AxisTitle.Text = "Time"
    .Axes(2, 1).HasTitle = True
    .Axes(2, 1).AxisTitle.Text = "Availability"
End With

' Export the chart as an image.
objChart.Export "c:/jenkins/workspace/CERT_Sanity_SOATest/portCleanUpJob2/src/main/resources/ApiMonitorImage.jpg"

Application.DisplayAlerts = False
ThisWorkbook.Save
Application.DisplayAlerts = True
Application.Quit

objWorkbook.Close
objExcel.Quit