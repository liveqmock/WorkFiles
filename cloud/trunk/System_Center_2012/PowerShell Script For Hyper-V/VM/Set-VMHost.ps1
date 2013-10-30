$lstr_ModifyServer                       = "Modify Server Configuration" 
$lstr_ModifyServerSuccess                = "Updated server settings on server {0}"
$lstr_ModifyServerFailure                = "Failed to Modify server {0} return code "
$lStr_ServerName                         = "Server: {0}" 


Function Set-VMHost
{# .ExternalHelp  MAML-VM.XML
    [CmdletBinding(SupportsShouldProcess=$true , ConfirmImpact='High' )]
    param(
        [ValidateNotNullOrEmpty()]
        [String]$ExtDataPath , 
        
        [ValidateNotNullOrEmpty()]
        [String]$VHDPath , 
        
        [ValidatePattern('^[0-9|a-f]{12}$')]
        [String]$MINMac, 
        
        [ValidatePattern('^[0-9|a-f]{12}$')]
        [String]$MaxMac ,
        
        [String]$OwnerContact ,
        [String]$OwnerName,
        [parameter(ValueFromPipeline = $true)][ValidateNotNullOrEmpty()]
        $Server = ".",    
        $PSC,      
        [switch]$Force
    )
    Process {
         if ($psc -eq $null)  {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}

        if ($Server.count -gt 1 )  {[Void]$PSBoundParameters.Remove("Server") ;  $Server | ForEach-object {Set-VMHost -Server $_  @PSBoundParameters}} 
        if ($Server -is [String]) {
            $VSMSSD   = Get-WmiObject -ComputerName $Server -NameSpace $HyperVNamespace -Class "MsVM_VirtualSystemManagementServiceSettingData"
            $VSMgtSvc = Get-WmiObject -ComputerName $Server -Namespace $HyperVNamespace -Class "MSVM_VirtualSystemManagementService"
            if ($ExtDataPath)  { $VSMSSD.DefaultExternalDataRoot    = $extDataPath  } 
            If ($vhdPath )     { $VSMSSD.DefaultVirtualHardDiskPath = $VhdPath      } 
            if ($MINMac)       { $VSMSSD.MinimumMacAddress          = $MinMac       } 
            If ($MaxMac)       { $VSMSSD.MaximumMacAddress          = $MaxMac       } 
            if ($OwnerContact) { $VSMSSD.PrimaryOwnerContact        = $ownerContact }
            if ($OwnerName)    { $VSMSSD.PrimaryOwnerName           = $OwnerName    } 
            if ($force -or $psc.shouldProcess(($lStr_ServerName -f $VSMSSD.__server ), $lstr_ModifyServer)) {
                $result = ( ($VSMgtSvc.ModifyServiceSettings($VSMSSD.GetText([System.Management.TextFormat]::WmiDtd20) ) | Test-wmiResult -wait -JobWaitText ($lstr_ModifyServer)`
                                -SuccessText ($lstr_ModifyServerSuccess -f $VSMSSD.__server) -failText ($lstr_ModifyServerFailure -f $VSMSSD.__Server) ) )      
            }
            $VSMSSD.get()  
            $VSMSSD | Select-Object -property  Caption, DefaultExternalDataRoot, DefaultVirtualHardDiskPath,MinimumMacAddress, MaximumMacAddress ,PrimaryOwnerContact, PrimaryOwnerName
        }
    }
}