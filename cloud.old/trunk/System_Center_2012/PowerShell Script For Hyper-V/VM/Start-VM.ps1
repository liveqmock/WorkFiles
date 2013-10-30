$lStr_VMStarting  = "Starting VM"
$lStr_WMIJobFailed1 = "The WMI Job failed: {0}"


Function Start-VM
{# .ExternalHelp  MAML-VM.XML
    [CmdletBinding(SupportsShouldProcess=$True)]
    Param(
        [parameter(Mandatory = $true,ValueFromPipeline = $true)]
        $VM, 
        
        [ValidateNotNullOrEmpty()]
        $Server = ".",    #May need to look for VM(s) on Multiple servers
        [Switch]$Wait,
        
        [Alias("TimeOut")]
        [int]$HeartBeatTimeOut ,
        $PSC,
        [Switch]$Force
    ) 
    Process { if ($psc -eq $null)  {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
             [Void]$PSBoundParameters.Remove("HeartBeatTimeOut")  
             Set-VmState -State ([VMState]::Running) @PSBoundParameters
             if ($HeartBeatTimeOut)  {Test-VMHeartBeat -VM $VM -timeOut $HeartBeatTimeOut -Server $Server}   
    }
}

