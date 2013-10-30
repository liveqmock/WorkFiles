

Function Get-VMIntegrationComponent
{# .ExternalHelp  MAML-VMConfig.XML
    param(
        [parameter(ValueFromPipeline = $true)]
        $VM="%",
        
        [ValidateNotNullOrEmpty()]
        $Server="."         #May need to look for VM(s) on Multiple servers
    )
    process {
        if ($VM -is [String]) {$VM=(Get-VM -Name $VM -Server $Server) }
        if ($vm.__CLASS -eq 'Msvm_ComputerSystem') { 
            (Get-vmSettingData -vm $vm -Server $Vm.__server).getRelated()  | Where-Object {$_.allocationUnits -eq "integration Components"}  |
               Add-Member -PassThru -Name "VMElementName" -MemberType NoteProperty -Value  $vm.elementName
        }
    }
} 