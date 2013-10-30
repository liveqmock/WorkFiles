

Function Remove-VMRemoteFXController
{# .ExternalHelp  MAML-vmConfig.XML
    [CmdletBinding(SupportsShouldProcess=$true)]
    Param(
        [parameter(Mandatory = $true, ValueFromPipeLine = $true)] 
        $VM, 
        
        [ValidateNotNullOrEmpty()]  
        $Server=".",                    #May need to look for VM(s) on Multiple servers
        $PSC, 
        [switch]$Force
    )
    process {
        if ($psc -eq $null)  {$psc = $pscmdlet}  ; if (-not $PSBoundParameters.psc) {$PSBoundParameters.add("psc",$psc)}
        if ($VM -is [String]) {$VM=(Get-VM -Name $VM -Server $Server) }
        if ($VM.count -gt 1 ) {[Void]$PSBoundParameters.Remove("VM") ;  $VM | ForEach-object {Remove-RemoteFXController -VM $_  @PSBoundParameters}}
        $controller= (Get-VMRemoteFXController -vm $vm )
        if ($Controller -is [System.Management.ManagementObject]) {
	        remove-VMRasd -VM $vm -rasd $controller -PSC $psc -force:$force
        }
    }	
}
