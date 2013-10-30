$lstr_RemoveHostNic = "Remove Virtual Nic from host"

Function Remove-VMSwitchNIC 
{# .ExternalHelp  MAML-VMNetwork.XML
    [CmdletBinding(SupportsShouldProcess=$True , ConfirmImpact='High')]
    param (
        [parameter(Mandatory = $true)]
        [String]$Name ,

        [ValidateNotNullOrEmpty()]  
        [string]$Server=".",  #Only process one server
        $PSC,
        [Switch]$Force
     )
     if ( $psc -eq $null)    { $psc = $pscmdlet }   
     $virtualSwitchMgtSvc= Get-WMIObject -computername $server -namespace $HyperVNamespace -class "MSVM_VirtualSwitchManagementService"   
     $nic                = get-wmiObject -computername $server -namespace $HyperVNamespace  -query "Select * from Msvm_InternalEthernetPort where elementName = '$Name' "
     if (($nic -is [System.Management.ManagementObject]) -and ($force -or $psc.shouldProcess($nic.ElementName, $lstr_RemoveHostNic ))) {     
         $result = $virtualSwitchMgtSvc.DeleteInternalEthernetPort($nic)
         [returnCode]$result.returnValue
    }
}

