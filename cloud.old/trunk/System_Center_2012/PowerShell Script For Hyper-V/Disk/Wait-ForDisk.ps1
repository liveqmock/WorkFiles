

Function Wait-ForDisk
{# .ExternalHelp  MAML-VMDisk.XML
    [CmdletBinding()]
    param(
        [parameter(Mandatory = $true)][ValidateNotNullOrEmpty()]
        [string]$MountPoint,
        [int]$Attempts = 10,
        [int]$MSPause = 1000
    )
    if ($mountPoint -notmatch "\:$") { $MountPoint += ":" }
    Write-Verbose "MountPoint = $MountPoint"
    do {
        if (-not (Get-WmiObject -Query "SELECT * FROM Win32_LogicalDisk WHERE DeviceID = '$MountPoint'")) { Start-Sleep -Milliseconds $MSPause}
        else {$Attempts = -1 }   
    } while ($Attempts-- -gt 0)
    [bool]$Attempts
}