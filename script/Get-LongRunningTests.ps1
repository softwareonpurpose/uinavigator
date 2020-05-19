$singleTestMaxTime=0;
$xmlFile=New-Object xml;
$testResults=gci .\build\test-results\ -Recurse -Filter *.xml;
$testResults|ForEach-Object {([xml](Get-Content $_.FullName)).testsuite} | Select-Object name, tests, time | Sort-Object tests, time;
# $testResults | ForEach-Object( {if ($_.time -gt $singleTestMaxTime) {$singleTestMaxTime=_$.time}});
# Write-Host $singleTestMaxTime,