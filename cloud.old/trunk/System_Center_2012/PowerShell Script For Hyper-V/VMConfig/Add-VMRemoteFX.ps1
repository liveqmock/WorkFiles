

Function Add-VMRemoteFXController
{# .ExternalHelp  MAML-vmConfig.XML
    [CmdletBinding(SupportsShouldProcess=$true)]
    Param (
        [parameter(Mandatory = $true, ValueFromPipeLine = $true)] 
        $VM,     
        
        [int][validateRange(1,4)]$Monitors = 1, 
        [RemoteFxResoultion]$Resolution=0,
        
        [ValidateNotNullOrEmpty()] 
        $Server = ".",     #May need to look for VM(s) on Multiple servers
        $PSC, 
        [switch]$Force
    )
    process {
        if ($psc -eq $null)   {$psc = $pscmdlet} ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
        if ($VM -is [String]) {$VM=(Get-VM -Name $VM -Server $Server) }
        if ($VM.count -gt 1 ) {[Void]$PSBoundParameters.Remove("VM") ;  $VM | ForEach-object {Add-VMRemoteFXController -VM $_ @PSBoundParameters}}
        if ($VM.__CLASS -eq 'Msvm_ComputerSystem') {
            $RFXRASD=NEW-VMRasd -ResType ([resourcetype]::GraphicsController) -ResSubtype 'Microsoft Synthetic 3D Display Controller' -Server $vm.__Server
            $RFXRASD.MaximumMonitors =  $monitors
            $RFXRASD.MaximumScreenResolution = $Resolution
            add-VmRasd -rasd $RFXRASD -vm $vm -PSC $psc -force:$force
       }
    }     
}