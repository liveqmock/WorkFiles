Function Get-VMHost
{# .ExternalHelp  MAML-VM.XML
    param(
        [parameter(ValueFromPipeline = $true)]
        [string]
        [ValidateNotNullOrEmpty()]
        $Domain=([ADSI]('GC://'+([ADSI]'LDAP://RootDse').RootDomainNamingContext))
    )   
    $searcher = New-Object directoryServices.DirectorySearcher($domain)
    $searcher.Filter = "(&(cn=Microsoft hyper-v)(objectCategory=serviceConnectionPoint))"
    # Find all matching service connection points in the container. Return 1 object for each, the name. 
    $searcher.FindAll() | ForEach-object {$_.Path.Split(",")[1] -Replace "CN=",""  }
} 
