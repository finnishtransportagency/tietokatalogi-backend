# Installing local environment

NOTE: THESE ARE OUTDATED, USE AT YOUR OWN RISK

## Prerequisites for Mac
* HomeBrew [(https://brew.sh)](https://brew.sh)
* VirtualBox [(https://www.virtualbox.org)](https://www.virtualbox.org/)
	* Preferred installation method `scripts/1_install_virtualbox_mac.sh`
* Maven [(https://maven.apache.org/)](https://maven.apache.org/)
	* Preferred installation method `brew install maven`

## Prerequisites for Windows 10
* Chocolatey [(https://chocolatey.org/install)](https://chocolatey.org/install)
* VirtualBox [(https://www.virtualbox.org)](https://www.virtualbox.org/)
* Maven [(https://maven.apache.org/)](https://maven.apache.org/)
	* Preferred installation method `choco install maven`

## Installing docker-containers
* Install Docker [(https://www.docker.com/get-docker)](https://www.docker.com/get-docker)
	* Install containers tomcat ja alexeiled/docker-oracle-xe-11g
	* Preferred installation method (Mac) `scripts/2_install_docker_mac.sh`
	* Preferred installation method (Win 10) `scripts/2_install_docker_win10.ps1`
	    * Windows may not allow running PowerShell scripts by default. Run the script as admin with `powershell -ExecutionPolicy ByPass -File <script name>.ps1
`. The execution policy can also changed beforehand with `Set-ExecutionPolicy unrestricted`, but this can be unsafe.

TODO: this script doesn't seem to work correctly on win10

## Starting local environment
Start local environment running command (Mac) `scripts/3_start_local_environments_mac.sh`
Start local environment running command (Win 10) `scripts/3_start_local_environments_win10.ps1`

## Stopping local environment
Stop local environment running command (Mac) `scripts/4_stop_local_environments_mac.sh`
Stop local environment running command (Win 10) `scripts/4_stop_local_environments_win10.ps1`

## Delete local environment
Delete docker-machine and both containers running command (Mac) `scripts/5_delete_docker_machine_mac.sh`
Delete docker-machine and both containers running command (Win 10) `scripts/5_delete_docker_machine_win10.ps1`

## Creating needed tables, inserting sampledata etc
Database management is documented in db-folder.

## Deploying tietokatalogi to tomcat-container
Build & deploy tietokatalogi to tomcat-container running command (Mac) `build-for-local-tomcat.sh` in main folder.

## SSH to tomcat-container
You can ssh to tomcat-container using command (Mac) `scripts/6_ssh_to_tomcat_mac.sh`
You can ssh to tomcat-container using command (Win 10) `scripts/6_ssh_to_tomcat_win10.ps1`

## Database information

| Variable | Value.    |
| -------- | --------- |
| Username | tietok.   |
| Password | livirules |
| Hostname | localhost |
| Port     | 1521      |
| SID      | xe.       |
