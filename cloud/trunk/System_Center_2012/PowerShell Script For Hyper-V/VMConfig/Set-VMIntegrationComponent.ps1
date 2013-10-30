

Function Set-VMIntegrationComponent
{# .ExternalHelp  MAML-VMConfig.XML
[CmdletBinding(SupportsShouldProcess=$true)]
    param(
        [parameter(ValueFromPipeline = $true)]
        $VM="%",
        [String[]]$ComponentName="*",
        [vmstate]$State,
        
        [ValidateNotNullOrEmpty()]
        $Server=".",      #May need to look for VM(s) on Multiple servers
        $PSC, 
        [switch]$force
    )
    process {
        if ($psc -eq $null)   {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
            foreach ($c in $componentName)  {(get-vmSettingData -vm $vm -server $server) | foreach-object {$_.getRelated() |          #get-wmiobject  -computername $vm.__SERVER -namespace $HyperVNamespace  -Query "associators of {$vmsd}" | 
                                              where-object {($_.allocationUnits -eq "integration Components") -and ($_.elementName -like "$c*")} | 
                                                  foreach-object {If (-not $state) {$_.enabledState = ($_.enabledState -bxor 1)} 
                                                                  else             {$_.enabledState = [int]$state}
                                                                  Set-VMRASD  -rasd $_ -PSC $psc -force:$force                                             
                                                  }                  
                                        }
        }
   }
} 
