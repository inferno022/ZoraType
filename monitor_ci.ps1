# GitHub Actions Monitor
param(
    [string]$RepoOwner = "inferno022",
    [string]$RepoName = "ZoraType"
)

Write-Host "🔍 Monitoring GitHub Actions for $RepoOwner/$RepoName" -ForegroundColor Green

# Function to check latest workflow run
function Get-LatestWorkflowRun {
    try {
        $url = "https://api.github.com/repos/$RepoOwner/$RepoName/actions/runs?per_page=1"
        $response = Invoke-RestMethod -Uri $url -Method Get
        return $response.workflow_runs[0]
    } catch {
        Write-Host "❌ Error fetching workflow data: $_" -ForegroundColor Red
        return $null
    }
}

# Monitor loop
$lastRunId = $null
while ($true) {
    $latestRun = Get-LatestWorkflowRun
    
    if ($latestRun -and $latestRun.id -ne $lastRunId) {
        $lastRunId = $latestRun.id
        
        Write-Host "`n🚀 New Workflow Run Detected!" -ForegroundColor Yellow
        Write-Host "  ID: $($latestRun.id)" -ForegroundColor White
        Write-Host "  Status: $($latestRun.status)" -ForegroundColor White
        Write-Host "  Conclusion: $($latestRun.conclusion)" -ForegroundColor White
        Write-Host "  Started: $($latestRun.created_at)" -ForegroundColor White
        Write-Host "  URL: $($latestRun.html_url)" -ForegroundColor Cyan
        
        if ($latestRun.conclusion -eq "failure") {
            Write-Host "❌ Build Failed - Check logs at: $($latestRun.html_url)" -ForegroundColor Red
            
            # Try to get job details
            try {
                $jobsUrl = "https://api.github.com/repos/$RepoOwner/$RepoName/actions/runs/$($latestRun.id)/jobs"
                $jobs = Invoke-RestMethod -Uri $jobsUrl -Method Get
                
                foreach ($job in $jobs.jobs) {
                    if ($job.conclusion -eq "failure") {
                        Write-Host "  Failed Job: $($job.name)" -ForegroundColor Red
                        Write-Host "  Job URL: $($job.html_url)" -ForegroundColor Cyan
                    }
                }
            } catch {
                Write-Host "  Could not fetch job details" -ForegroundColor Yellow
            }
        } elseif ($latestRun.conclusion -eq "success") {
            Write-Host "✅ Build Succeeded!" -ForegroundColor Green
        }
    }
    
    Start-Sleep -Seconds 30
}