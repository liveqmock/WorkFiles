Function Test-WMIJob 
{# .ExternalHelp  Maml-Helper.XML
    [CmdletBinding()]
    param(
        [parameter(Mandatory = $true, ValueFromPipeline = $true)]
	[Alias("JobID")][ValidateNotNullOrEmpty()]
        [WMI]
        $Job,
        
        [switch]$StatusOnly,        

        [Switch]$Wait, 
        
        [string]$Description = "Job"
    )
       
    while (($Job.JobState -eq [JobState]::Running) -and $Wait)     { 
        Write-Progress -activity ("$Description $($Job.Caption)") -Status "% complete" -PercentComplete $Job.PercentComplete
        Start-Sleep -Milliseconds 250
        $Job.PSBase.Get() 
    }
    Write-Progress -activity ("$Description $($Job.Caption)") -Status "% complete" -Completed
    if ($Statusonly )  {([jobstate]$job.JobState)} else {$job}
}