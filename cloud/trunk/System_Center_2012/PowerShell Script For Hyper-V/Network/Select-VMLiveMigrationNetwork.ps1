$lstr_noCluster  = "Cluster commands not loaded. Import-Modue FailoverClusters and try again"
$lstr_SelectLMNetworks = "Please enter the ID(s) of the Network(s) to use for live migration, in order of preference"
$lstr_UpdateLiveMig="Update Live migration networks"

Function Select-VMLiveMigrationNetwork
{# .ExternalHelp  MAML-VMNetwork.XML
[CmdletBinding(SupportsShouldProcess=$true  , ConfirmImpact='High' )]
     Param (
        [ValidateNotNullOrEmpty()] 
        $Server=".",
        $PSC, 
        [switch]$Force
     )
     if (-not (get-command -Name Move-ClusterVirtualMachineRole -ErrorAction "SilentlyContinue")) {Write-warning $lstr_noCluster ; return} 
     if ($psc -eq $null)  {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
     $clusternetworks = Get-ClusterNetwork -Cluster $server           
     write-host -ForegroundColor Red $lstr_SelectLMNetworks
     $selectedNetworks = Select-List -InputObject $clusternetworks -Property name,address -multi
     if ($force -or $psc.shouldProcess(($lStr_ServerName -f $server),$lstr_UpdateLiveMig)) {
         $selectedNetworks | ForEach-Object -Begin {$IncludedList =  ""          } `
                                          -process {$includedList += $_.id + ";" } `
                                              -end {get-ClusterResourceType -Cluster $Server -Name "virtual machine" | 
                                                    set-ClusterParameter -Name migrationNetworkOrder -Value ($includedList -replace "\;$","") }
         $clusternetworks | Where-Object {$selectedNetworks -notcontains $_} | 
                                ForEach-Object -Begin {$ExcludedList =  ""         } `
                                             -process {$ExcludedList += $_.id + ";"} `
                                                 -end {get-ClusterResourceType -Cluster $Server -Name "virtual machine" | 
                                                       set-ClusterParameter -Name "migrationExcludeNetworks" -Value ($ExcludedList -replace "\;$","")}
     }                                           
}                                  