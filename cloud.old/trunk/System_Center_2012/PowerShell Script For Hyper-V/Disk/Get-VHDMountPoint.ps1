

Function Get-VHDMountPoint 
{# .ExternalHelp  MAML-VMDisk.XML
    param(
        [parameter(Mandatory = $true, ValueFromPipelineByPropertyName =$true,ValueFromPipeline =$true)][Alias("Fullname","Path","DiskPath")][ValidateNotNullOrEmpty()]
        [string[]]$VHDPaths              #Accept One string, multiple string, or convert objects to string from one of their properties. 
    )
    Process {
        Foreach ($vhdPath in $vhdPaths) {
            if ($VHDPath -notmatch "(\w:|\w)\\\w") { $VHDPath = Join-Path $(Get-VhdDefaultPath $Server) $VHDPath }
            if ($vhdpath -notmatch ".VHD$" )       {$vhdPath += ".VHD"}
            $VHDPath = (Resolve-Path $VHDPath).path
    	    $MountedDiskImage = Get-WmiObject -Namespace $HyperVNamespace -Query ("select * from Msvm_MountedStorageImage where name='" + $vhdpath.replace("\","\\") + "' ")
            if ($MountedDiskImage) {(Get-WmiObject -Query ("SELECT * FROM Win32_DiskDrive WHERE Model='Msft Virtual Disk SCSI Disk Device' " + 
                                                                                         " AND ScsiTargetID=$($MountedDiskImage.TargetId)  " + 
                                                                                         " AND ScsiLogicalUnit=$($MountedDiskImage.Lun)    " + 
                                                                                        " AND ScsiPort=$($MountedDiskImage.PortNumber)"
                                                         )).getRelated("Win32_DiskPartition") | foreach-object {$_.getRelated("win32_logicalDisk")}
            }
        }                       
    }
}