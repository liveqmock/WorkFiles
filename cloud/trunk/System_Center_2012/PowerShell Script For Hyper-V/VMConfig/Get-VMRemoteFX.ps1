

Function Get-VMRemoteFXController
{# .ExternalHelp  MAML-vmConfig.XML
    [CmdletBinding()]
    param(
        [parameter(ValueFromPipeline = $true)]
        $VM = "%" ,
        
        [ValidateNotNullOrEmpty()] 
        $Server = "."  #May need to look for VM(s) on Multiple servers

    )
    Process {
       get-vmsettingData -vm $vm -server $SERVER | foreach-object { $_.getRelated("Msvm_Synthetic3DDisplayControllerSettingData")  }
  
    }
}