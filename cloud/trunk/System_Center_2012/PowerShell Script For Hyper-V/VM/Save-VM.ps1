$lStr_VMSaving   = "Suspending VM"
$lStr_WMIJobFailed1 = "The WMI Job failed: {0}"


Function Save-VM
{# .ExternalHelp  MAML-VM.XML
    [CmdletBinding(SupportsShouldProcess=$true, ConfirmImpact='High')]
    Param(
        [parameter(Mandatory = $true, ValueFromPipeline = $true)]
        $VM, 
        
        [ValidateNotNullOrEmpty()]
        $Server = ".",      #May need to look for VM(s) on Multiple servers        
        [Switch]$Wait,
        $PSC,
        [Switch]$Force
    ) 
    Process { if ($psc -eq $null)  {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
              Set-VmState -State ([VMState]::Suspended) @PSBoundParameters } 
}