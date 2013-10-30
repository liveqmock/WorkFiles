$LStr_WMIJobFailed2                  = "The WMI Job failed with status {0} `n {1} "
$Lstr_CreateVFD                      = "Create VFD {0}"
$lstr_CreateVFDFailed                = "Failure while creating VFD.  Error: {0}" 
$lStr_VirtualMachineName             = "VM: {0}"
$Lstr_VFDCreationContinues           = "The job to create the VFD {0} has not finished. You can use Test-WmiJob to check progress, the ID is:`n {1}"
$Lstr_VfDCreationSuccess            = "The job to create the VFD {0} completed successfully"

Function New-VFD
{# .ExternalHelp  MAML-VMDisk.XML
    param(
        [parameter(Mandatory = $true, ValueFromPipeLine = $true)][Alias("path","FullName")]
        $VFDPaths, 
        
        [ValidateNotNullOrEmpty()] 
        [string]$Server = ".",  #Only work with images on one server  
        [Switch]$wait
    )
    process {
        foreach ($VFDPath in $VFDPaths) {
            $ImageManagementService = Get-WmiObject -ComputerName $server -NameSpace $HyperVNamespace -Class $ImageManagementServiceName
            if ($VFDPath -notmatch "(\w:|\w)\\\w")  {$vfdPath = (Get-VHDDefaultPath -Server $server) + '\' + $vfdPath }
            if ($VFDpath -notmatch ".VFD$" )        {$VFDPath += ".VFD"}
            $result = $ImageManagementService.CreateVirtualFloppyDisk($vfdPath)
            if     ( $result.returnValue -eq [ReturnCode]::OK)         {Write-Verbose ($Lstr_VfDCreationSuccess    -f $VfDPath) ;  [System.IO.FileInfo]$vfdPath  }
            elseif ( $result.returnValue -eq [ReturnCode]::JobStarted) {
                $job = Test-WMIJob $result.job -Wait:$wait -Description ($Lstr_CreateVFD -f $vfdPath )
                if ($job.JobState -eq [JobState]::Completed)   {Write-Verbose ($Lstr_VfDCreationSuccess    -f $VfDPath) ; [System.IO.FileInfo]$vfdPath  }
                elseif (-not $wait -and ($job.jobState -eq [JobState]::running)) {Write-Warning ($Lstr_VFDCreationContinues    -f $VfDPath,$result.job) }
                else   {write-error ($LStr_WMIjobfailed2 -f [JobState]$job.JobState, $job.ErrorDescription) }       
            }
            else  {write-error ($lstr_CreateVFDFailed -f [ReturnCode]$result.returnValue ) }
        }
    }
}