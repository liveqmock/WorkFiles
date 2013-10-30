$lstr_noCluster  = "Cluster commands not loaded. Import-Modue FailoverClusters and try again"


Function Get-VMLiveMigrationNetwork
{# .ExternalHelp  MAML-VMNetwork.XML
    param(
        [parameter(ValueFromPipeLine = $true)][Alias("Cluster")][ValidateNotNullOrEmpty()] 
        $Server = "."
     )
    Process {
        if (-not (get-command -Name Move-ClusterVirtualMachineRole -ErrorAction "SilentlyContinue")) { Write-warning $lstr_noCluster  ; return }
        $Netorder= (Get-ClusterResourceType -Cluster $server -Name "virtual machine" | Get-ClusterParameter -Name migrationNetworkOrder).value.split(";")
        If ($netOrder) { foreach ($id in $netorder) {get-clusterNetwork -Cluster $server | where-object {$_.id -eq $id} }}
        Else {get-clusterNetwork | 
            where-object {(Get-ClusterResourceType -Cluster $server -Name "virtual machine" | 
                Get-ClusterParameter -Name migrationExcludeNetworks).value.split(";") -notcontains $_.id}}
    }
}