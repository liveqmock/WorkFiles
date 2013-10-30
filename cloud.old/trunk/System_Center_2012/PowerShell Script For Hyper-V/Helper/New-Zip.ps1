Function New-Zip
{<#
    .SYNOPSIS
        Creates a new , empty Zip file
#>
    [CmdletBinding()]
    param (
        [Parameter(mandatory = $True)][ValidateNotNullOrEmpty()]
        [string]$ZIPFile
    )
    If ( $ZIPFile -notMatch "ZIP$")  { $ZIPFile += ".ZIP" } 
    set-content $ZIPFile ("PK" + [char]5 + [char]6 + ("$([char]0)" * 18))
    Start-Sleep -Seconds 2
    Get-ChildItem $ZIPFile
}