Set objExcel = CreateObject("Excel.Application")
objExcel.Visible = True

Set objWorkbook = objExcel.Workbooks.Open("C:\Users\AC70068\eclipse-workspace\SBA\ApiMonitor\apiChart.xlsx")
Set objWorksheet = objWorkbook.Worksheets(1)

' Assume that the data is in range A1:B10
Set objRange = objWorksheet.Range("C1:C24")

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
objChart.Export "C:\Users\AC70068\eclipse-workspace\SBA\ApiMonitor\ApiMonitorImage.jpg"

Application.ActiveWindow.Close SaveChanges:=True
ActiveWorkbook.Close SaveChanges:=True

objWorkbook.Close
objExcel.Quit