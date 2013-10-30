$lstr_ModifyHW                       = "Modify {0}" 
$lstr_ModifyHWSuccess                = "Modified {0} on {1}."
$lstr_ModifyHWFailure                = "Failed to Modify {0} on {1}, result was"


Function Set-VMRASD 
{# .ExternalHelp  MAML-VMConfig.XML
    [CmdletBinding(SupportsShouldProcess=$true)]
    param(
        [System.Management.ManagementObject]$VM , #VM no longer needed but kept for compatibility
        [parameter(Mandatory = $true)]
        $RASD ,
        $PSC, 
        [switch]$Force
    )
        if ($psc -eq $null) { $psc = $pscmdlet }
        $VSMgtSvc = Get-WmiObject -ComputerName $rasd.__Server -Namespace $HyperVNamespace -Class "MSVM_VirtualSystemManagementService"
        $VM  = Get-VM $rasd 
        if ($force -or $psc.shouldProcess(($lStr_VirtualMachineName -f $vm.elementName ), ($lstr_ModifyHW   -f $Rasd.ElementName))) {
             if ( ($VSMgtSvc.ModifyVirtualSystemResources($VM.__Path, @($Rasd.GetText([System.Management.TextFormat]::WmiDtd20)) ) |
                     Test-wmiResult -wait  -JobWaitText ($lstr_ModifyHW -f $Rasd.ElementName)`
                                    -SuccessText ($lstr_ModifyHWSuccess -f $Rasd.ElementName, $VM.elementname) `
                                    -failText    ($lstr_ModifyHWFailure -f $Rasd.ElementName, $vm.elementname) ) -eq [returnCode]::ok) { 
                                    IF ((Get-Module FailoverClusters) -and (Get-vmclusterGroup $VM)) {Sync-VMClusterConfig $vm | out-null }
                                    $rasd.get() ;  $rasd
             }                       
        }
}